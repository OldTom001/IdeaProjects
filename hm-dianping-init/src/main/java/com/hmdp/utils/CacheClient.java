package com.hmdp.utils;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
@Component
public class CacheClient {


    private final StringRedisTemplate stringRedisTemplate;
    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    public CacheClient(StringRedisTemplate stringRedisTemplate){
        this.stringRedisTemplate = stringRedisTemplate;
    }

    //将任意java对象序列化为json并存储到缓存中, 可以设置TTL
    public void set(String key, Object value, Long time, TimeUnit unit){
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, unit);
    }
    //将任意java对象序列化为json并存储到缓存中, 并设置逻辑过期
    public  void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit){
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));

        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

    //根据key查询缓存, 并反序列化为java对象, 利用缓存空值解决缓存穿透问题
    public <R, ID> R queryWithPassThrough(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit){
        String key = keyPrefix+id;
        String json = stringRedisTemplate.opsForValue().get(key);
        if(StrUtil.isNotBlank(json)){
            return JSONUtil.toBean(json, type);
        }
        if(json != null){ //json是""
            return null;
        }
        //缓存不存在, 查询数据库
        R r = dbFallback.apply(id);
        //数据库不存在
        if(r == null){
            stringRedisTemplate.opsForValue().set(key, "", RedisConstants.CACHE_NULL_TTL, TimeUnit.MINUTES);
            return null;
        }
        //数据库存在
        this.set(key, JSONUtil.toJsonStr(r), time, unit);
        return r;
    }

    //根据key查询缓存, 并反序列化为java对象, 利用逻辑过期解决缓存基础击穿问题
    public <R, ID> R queryWithLogicalExpire(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit){
        String key = keyPrefix+id;
        String json = stringRedisTemplate.opsForValue().get(key);
        //缓存未命中直接返回null
        if(StrUtil.isBlank(json)){
            return null;
        }
        RedisData redisData = JSONUtil.toBean(json, RedisData.class);
        R r = JSONUtil.toBean((JSONObject)redisData.getData(), type);
        LocalDateTime expireTime = redisData.getExpireTime();
        if(expireTime.isAfter(LocalDateTime.now())){
            return r;
        }
        String lockKey = RedisConstants.LOCK_SHOP_KEY + id;
        boolean islock = tryLock(lockKey);
        //成功获得互斥锁, 开辟一个新线程更新缓存
        if(islock){
            CACHE_REBUILD_EXECUTOR.submit(()->{
                try {
                    R newR = dbFallback.apply(id);
                    this.setWithLogicalExpire(key, newR, time, unit);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    unlock(lockKey);
                }
            });
        }
        //(不管有没有成功获得互斥锁)返回过期数据
        return r;
    }

    //根据key查询缓存, 并反序列化为java对象, 利用互斥锁基础击穿问题
    public <R, ID> R queryWithMutex(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit){
        String key = keyPrefix+id;
        String json = stringRedisTemplate.opsForValue().get(key);
        //缓存命中直接返回
        if(StrUtil.isNotBlank(json)){
            return JSONUtil.toBean(json, type);
        }
        //命中空值
        if(json != null){
            return null;
        }
        //未命中, 互斥锁
        R r = null;
        String lockKey  = RedisConstants.LOCK_SHOP_KEY+id;
        try {
            boolean isLock = tryLock(lockKey);
            //未成功获得锁, 等一会再试试
            if(!isLock){
                Thread.sleep(50);
                return queryWithMutex(keyPrefix, id, type, dbFallback, time ,unit);
            }
            //成功获得锁
            r = dbFallback.apply(id);
            if(r == null){
                return null;
            }
            this.set(key,r,time,unit);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            unlock(lockKey);
        }

        return r;
    }

    private boolean tryLock(String key) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }

    private void unlock(String key) {
        stringRedisTemplate.delete(key);
    }
}
