package com.hmdp;

import com.hmdp.service.IShopService;

import com.hmdp.utils.RedisIdWorker;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class HmDianPingApplicationTests {

    @Resource
    private IShopService shopService;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private RedissonClient redissonClient;

    private ExecutorService es = Executors.newFixedThreadPool(500);
    @Test
    // 商铺缓存预热
    void testSaveShop(){
        shopService.saveShop2Redis(1L, 10L);
    }

    @Test
    void testGetTime(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp.getTime());
    }

    @Test
    //每个线程生成100个id, 共300个线程
    void testIdWorker() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(300);
        Runnable task = ()->{
            for (int i = 0; i < 100; i++) {
                long id = redisIdWorker.nextId("order");
                System.out.println("id = " + id);
            }
            latch.countDown();
        };
        long begin = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            es.submit(task);
        }
        latch.await(); //阻塞, 等latch中维护的变量变成0, 再向下执行. 为了统计时间的准确性.
        long end = System.currentTimeMillis();
        System.out.println("time = " + (end - begin));
    }

    @Test
    void testRedisson() throws InterruptedException {
        RLock lock = redissonClient.getLock("anyLock");
        boolean isLock = lock.tryLock(1, 100, TimeUnit.SECONDS);
        if(isLock){
            try {
                System.out.println("执行业务");
            } finally {
                //lock.unlock();
            }
        }else{
            System.out.println("获取锁失败");
        }
    }






}
