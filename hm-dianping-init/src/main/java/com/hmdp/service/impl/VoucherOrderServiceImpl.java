package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.hmdp.dto.Result;
import com.hmdp.entity.VoucherOrder;
import com.hmdp.mapper.VoucherOrderMapper;
import com.hmdp.service.ISeckillVoucherService;
import com.hmdp.service.IVoucherOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.utils.RedisIdWorker;
import com.hmdp.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Service
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@Slf4j
public class VoucherOrderServiceImpl extends ServiceImpl<VoucherOrderMapper, VoucherOrder> implements IVoucherOrderService {
    @Resource
    private ISeckillVoucherService seckillVoucherService;
    @Resource
    private RedisIdWorker redisIdWorker;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private RedissonClient redissonClient;

    private IVoucherOrderService proxy;

    //秒杀脚本, 判断秒杀资格
    private static final DefaultRedisScript<Long> SECKILL_SCRIPT;

    static {
        SECKILL_SCRIPT = new DefaultRedisScript<>();
        SECKILL_SCRIPT.setLocation(new ClassPathResource("seckill.lua"));
        SECKILL_SCRIPT.setResultType(Long.class);
    }
    //异步处理线程池
    private static final ExecutorService SECKILL_ORDER_EXECUTOR = Executors.newSingleThreadExecutor();



    @PostConstruct
    private void init(){
        SECKILL_ORDER_EXECUTOR.submit(new VoucherOrderHandler());
    }

    /**
     * 异步创建订单逻辑, redis stream消息队列实现
     */
    private BlockingQueue<VoucherOrder> orderTasks =new ArrayBlockingQueue<>(1024 * 1024);
    String queueName = "stream.orders";
    private class VoucherOrderHandler implements Runnable{

        @Override
        public void run() {
            while (true) {
                try {
                    //XREADGROUP GROUP g1 c1 COUNT 1 block 2000 stream.orders >, 读取最近一条未消费的消息
                    List<MapRecord<String, Object, Object>> list = stringRedisTemplate.opsForStream().read(
                            Consumer.from("g1", "c1"),
                            StreamReadOptions.empty().count(1).block(Duration.ofSeconds(2)),
                            StreamOffset.create(queueName, ReadOffset.lastConsumed())
                    );
                    if(list == null || list.isEmpty()){
                        continue;
                    }
                    MapRecord<String, Object, Object> record = list.get(0); //count(1)已经明确队列中只有一个消息
                    Map<Object, Object> values = record.getValue();
                    VoucherOrder voucherOrder = BeanUtil.fillBeanWithMap(values, new VoucherOrder(), true);
                    handleVoucherOrder(voucherOrder);
                    //ACK确认
                    stringRedisTemplate.opsForStream().acknowledge(queueName, "g1", record.getId());


                } catch (Exception e) {
                    log.error("处理订单异常", e);
                    handlePendingList();
                }
            }
        }
    }

    private void handlePendingList() {
        while (true) {
            try {
                //XREADGROUP GROUP g1 c1 COUNT 1 block 2000 stream.orders >, 读取最近一条未消费的消息
                List<MapRecord<String, Object, Object>> list = stringRedisTemplate.opsForStream().read(
                        Consumer.from("g1", "c1"),
                        StreamReadOptions.empty().count(1),
                        StreamOffset.create(queueName, ReadOffset.from("0"))
                );//0表示读取pendingList;
                if(list == null || list.isEmpty()){ //没有异常消息, 结束循环即可
                    break;
                }
                MapRecord<String, Object, Object> record = list.get(0); //count(1)已经明确队列中只有一个消息
                Map<Object, Object> values = record.getValue();
                VoucherOrder voucherOrder = BeanUtil.fillBeanWithMap(values, new VoucherOrder(), true);
                handleVoucherOrder(voucherOrder);
                //ACK确认
                stringRedisTemplate.opsForStream().acknowledge(queueName, "g1", record.getId());


            } catch (Exception e) {
                log.error("处理pendingList订单异常", e);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    /**
     * 异步创建订单逻辑, 阻塞队列实现
     */
   /* private BlockingQueue<VoucherOrder> orderTasks =new ArrayBlockingQueue<>(1024 * 1024);
    private class VoucherOrderHandler implements Runnable{
        @Override
        public void run() {
            while (true) {
                try {
                    VoucherOrder voucherOrder = orderTasks.take();
                    handleVoucherOrder(voucherOrder);
                } catch (Exception e) {
                    log.error("处理订单异常", e);
                }
            }
        }
    }*/

    private void handleVoucherOrder(VoucherOrder voucherOrder){
        Long userId = voucherOrder.getUserId();
        RLock lock = redissonClient.getLock("lock:order");
        boolean isLock = lock.tryLock(); ////若有别的线程(相同用户)在下单, 则不能获取锁. 这里是个冗余设计, 理论上不用加锁
        if(!isLock){
            log.error("不允许重复下单!");
        }
        //proxy = (IVoucherOrderService)AopContext.currentProxy(); //这里拿不到代理对象, 因为AopContext.currentProxy()底层是基于ThreadLocal实现的
        proxy.createVoucherOrder(voucherOrder);
        return;
    }

    /**
     * 串行下单, 基于数据库和分布式锁实现
     */
/*    @Override
    public Result seckillVoucher(Long voucherId) {
        SeckillVoucher voucher = seckillVoucherService.getById(voucherId);
        if(voucher.getBeginTime().isAfter(LocalDateTime.now())){
            return Result.fail("秒杀尚未开始!");
        }
        if(voucher.getEndTime().isBefore(LocalDateTime.now())){
            return Result.fail("秒杀已经结束!");
        }
        if(voucher.getStock()<1){
            return Result.fail("库存不足!");
        }
        Long userId = UserHolder.getUser().getId();

        *//*
        创建分布式锁
        *//*
*//*        SimpleRedisLock lock = new SimpleRedisLock("order:" + userId, stringRedisTemplate);
        boolean isLock = lock.tryLock(1200);
        if(!isLock){
            return Result.fail("不允许重复下单");
        }*//*

        *//*
        * 使用redisson创建分布式锁
        * *//*
        RLock lock = redissonClient.getLock("lock:order" + userId);
        boolean isLock = lock.tryLock();
        if(!isLock){
            return Result.fail("不允许重复下单");
        }
// 加入分布式锁后, 不再需要synchronized
*//*        synchronized (userId.toString().intern()) {
            IVoucherOrderService proxy = (IVoucherOrderService) AopContext.currentProxy();
            return proxy.createVoucherOrder(voucherId);
        }*//*

        try {
            IVoucherOrderService proxy = (IVoucherOrderService) AopContext.currentProxy();
            return proxy.createVoucherOrder(voucherId);
        } finally {
            lock.unlock();
        }

    }*/
    /**
     * redis stream消息队列实现异步下单, 扣减库存阶段: 基于redis和lua脚本实现; 创建订单阶段: 新建线程实现
     * @param voucherId
     * @return
     */
    @Override
    public Result seckillVoucher(Long voucherId) {
        Long userId = UserHolder.getUser().getId();
        long orderId = redisIdWorker.nextId("order");

        //判断库存, 一人一单, 若判断成功, 则添加到消息队列.
        //需要先在redis-cli中执行 XGROUP CREATE stream.orders g1 0 MKSTREAM 创建消息队列和消费者组
        Long result = stringRedisTemplate.execute(
                SECKILL_SCRIPT,
                Collections.emptyList(),
                voucherId.toString(), userId.toString(), String.valueOf(orderId)
        );//lua脚本需要传字符串
        int r = result.intValue();
        if(r!=0){
            return Result.fail(r==1?"库存不足":"不能重复下单");

        }


        proxy = (IVoucherOrderService)AopContext.currentProxy(); //供子线程(创建订单)使用, 需要使用成员变量.
        return Result.ok(orderId);
    }

    /**
     * 阻塞队列实现异步下单, 扣减库存阶段: 基于redis和lua脚本实现; 创建订单阶段: 新建线程实现
     * @param voucherId
     * @return
     */
   /* @Override
    public Result seckillVoucher(Long voucherId) {
        Long userId = UserHolder.getUser().getId();
        long orderId = redisIdWorker.nextId("order");

        Long result = stringRedisTemplate.execute(
                SECKILL_SCRIPT,
                Collections.emptyList(),
                voucherId.toString(), userId.toString(), String.valueOf(orderId)
        );//阻塞队列实现时, 实际上不需要传入orderId
        int r = result.intValue();
        if(r!=0){
            return Result.fail(r==1?"库存不足":"不能重复下单");

        }
        VoucherOrder voucherOrder = new VoucherOrder();
        voucherOrder.setId(orderId);
        voucherOrder.setVoucherId(voucherId);
        voucherOrder.setUserId(userId);

        //保存到阻塞队列
        orderTasks.add(voucherOrder);
        proxy = (IVoucherOrderService)AopContext.currentProxy(); //供子线程(创建订单)使用, 需要使用成员变量.
        return Result.ok(orderId);
    }*/


    /**
     * 向数据库写入订单, 串行秒杀实现
     * @param voucherId
     * @return
     */
   /* @Transactional
    @Override
    public Result createVoucherOrder(Long voucherId) {
        Long userId = UserHolder.getUser().getId();
        // 5.1.查询订单
        int count = query().eq("user_id", userId).eq("voucher_id", voucherId).count();
        // 5.2.判断是否存在
        if (count > 0) {
            // 用户已经购买过了
            return Result.fail("用户已经购买过一次！");
        }

        // 6.扣减库存
        boolean success = seckillVoucherService.update()
                .setSql("stock = stock - 1") // set stock = stock - 1
                .eq("voucher_id", voucherId).gt("stock", 0) // where id = ? and stock > 0
                .update();
        if (!success) {
            // 扣减失败
            return Result.fail("库存不足！");
        }

        // 7.创建订单
        VoucherOrder voucherOrder = new VoucherOrder();
        // 7.1.订单id
        long orderId = redisIdWorker.nextId("order");
        voucherOrder.setId(orderId);
        // 7.2.用户id
        voucherOrder.setUserId(userId);
        // 7.3.代金券id
        voucherOrder.setVoucherId(voucherId);
        save(voucherOrder);

        // 7.返回订单id
        return Result.ok(orderId);
    }*/

    /**
     * 向数据库写入订单, 异步秒杀实现
     * 在秒杀资格判断阶段已经生成了voucherOrder对象, 传入即可.
     * 在秒杀资格判断阶段已经返回了下单信息, 因此返回void.
     * @param voucherOrder
     */
    @Transactional
    @Override
    public void createVoucherOrder(VoucherOrder voucherOrder) {
//        Long userId = UserHolder.getUser().getId();//因为是子线程, 所以不能在ThreadLocal中获取
        Long userId = voucherOrder.getUserId();
        // 5.1.查询订单
        int count = query().eq("user_id", userId).eq("voucher_id", voucherOrder.getVoucherId()).count();
        // 5.2.判断是否存在
        if (count > 0) { //冗余设计, 理论上不可能
            // 用户已经购买过了
           log.error("用户已经购买过了");
        }

        // 6.扣减库存
        //冗余设计, 理论上不可能库存不足和重复下单
        boolean success = seckillVoucherService.update()
                .setSql("stock = stock - 1") // set stock = stock - 1
                .eq("voucher_id", voucherOrder.getVoucherId()).gt("stock", 0) // where id = ? and stock > 0
                .update();
        if (!success) {
            // 扣减失败
            log.error("库存不足");
        }
        save(voucherOrder);
    }
}
