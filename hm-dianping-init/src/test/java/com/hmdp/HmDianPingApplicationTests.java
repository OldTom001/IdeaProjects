package com.hmdp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.hmdp.controller.UserController;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.service.IShopService;

import com.hmdp.service.IUserService;
import com.hmdp.utils.RedisIdWorker;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;
import static com.hmdp.utils.RedisConstants.LOGIN_CODE_TTL;

@SpringBootTest
class HmDianPingApplicationTests {

    @Resource
    private IShopService shopService;

    @Resource
    private RedisIdWorker redisIdWorker;

    @Resource
    private RedissonClient redissonClient;

    @Resource
    JdbcTemplate jdbcTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    UserController userController;

    @Resource
    IUserService iUserService;

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

    /**
     * 添加用户和登陆信息(手机号+token)到数据库和redis
     */
    @Test
//    @Transactional
    void addUserToDbAndRedis(){
        String[] phones = new String[1000];

        long userBase = 2001;
        for(int i = 0; i<1000; i++){
//            String token = UUID.randomUUID().toString(true);
//            // 7.3.存储
//            String tokenKey = LOGIN_USER_KEY + token;
//            stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
//            // 7.4.设置token有效期
//            stringRedisTemplate.expire(tokenKey, LOGIN_USER_TTL, TimeUnit.MINUTES);

            // 4.保存验证码到 session
            // 键, 值, 有效时间, 时间单位
            phones[i] = RandomStringUtils.randomNumeric(11);
            userBase++;
        }
        jdbcTemplate.batchUpdate("insert into tb_user(phone) values(?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                //1是要插入的字段在表中的索引, 从0开始记, phone是tb_user的第1个字段
                //phones[i]是第i次要插入的值, 一共插入phones.length次.
                preparedStatement.setString(1,phones[i]);
            }
            @Override
            public int getBatchSize() {
                return phones.length;
            }
        });
        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select id, phone from tb_user");
        for (Map<String, Object> map : maps) {
            UserDTO userDTO = new UserDTO();
            BigInteger id = (BigInteger) map.get("id");
            long l = id.longValue();
            userDTO.setId(Long.valueOf(l));
            userDTO.setNickName("test");
            userDTO.setIcon("test");
            String token = UUID.randomUUID().toString(true);

            Map<String, Object> userMap = BeanUtil.beanToMap(userDTO, new HashMap<>(),
                    CopyOptions.create()
                            .setIgnoreNullValue(true)
                            .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));
            // 7.3.存储
            String tokenKey = LOGIN_USER_KEY + token;
            stringRedisTemplate.opsForHash().putAll(tokenKey, userMap);
            // 7.4.设置token有效期
            stringRedisTemplate.expire(tokenKey, LOGIN_USER_TTL, TimeUnit.MINUTES);
        }
    }
    @Test
    void tokensToTxt() throws IOException {
        //读取redis中的token
        Set<String> keysWithPrefix = stringRedisTemplate.keys("login:token:".concat("*"));
        for (String key : keysWithPrefix) {
            key = key.split(":")[2];
        }
        //创建txt文件
        File directory = new File("");
        String path = directory.getCanonicalPath() + "\\tokens.txt";
        System.out.println(path);
        File tokensTxt = new File(path);
        tokensTxt.createNewFile();
        //TODO 将txt文件写入token


    }


}
