package com.dx.xjd.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class RedisComponent {

    @SuppressWarnings("rawtypes")
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
            log.info("one record is deleted from redis the key is : " + key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean exists(final String key) {
        boolean result = false;
        result = redisTemplate.hasKey(key);
        log.info("one record exists from redis the key is : " + key + " , result is " + result);
        return result;
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public Object get(final String key) {
        try {
            Object result = null;
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.get(key);
            log.info("get a record from redis key is : " + key + " , result is: " + result);
            return result;
        } catch (Exception e) {
            log.error("an error occoured when get one record from redis key is: " + key, e);
        }
        return null;
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T get(final String key, Class<T> clazz) {
        try {
            String result = null;
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            result = operations.get(key).toString();
            log.info("get a record from redis key is : " + key + " , result is: " + result);
            return JSONObject.parseObject(result, clazz);
        } catch (Exception e) {
            log.error("an error occoured when get one record from redis key is: " + key, e);
        }
        return null;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object object) {
        boolean result = false;
        try {
            String value = JSON.toJSONString(object);
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
            log.info("add on recorde to redis! " + value + " , result is :  " + result);
        } catch (Exception e) {
            log.error("an erro occour when put a record to redis", e);
        }
        return result;
    }

    /**
     * 写入缓存
     *
     * @param key        键
     * @param object     值
     * @param expireTime 过期时间(秒)
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean set(final String key, Object object, Long expireTime) {
        boolean result = false;
        try {
            String value = JSON.toJSONString(object);
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            result = redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            log.info("add one recorde " + value + " to redis with time :" + expireTime + " seconds , result is : " + result);
        } catch (Exception e) {
            log.error("when add on recorde to redis with time fail", e);
        }
        return result;
    }

}