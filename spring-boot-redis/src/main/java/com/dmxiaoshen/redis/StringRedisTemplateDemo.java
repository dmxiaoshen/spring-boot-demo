package com.dmxiaoshen.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by hzhsg on 2017/12/4.
 */
@Component
public class StringRedisTemplateDemo {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public void setValue(String key, String value, long expire) {
        if (expire <= 0) {
            stringRedisTemplate.opsForValue().set(key, value);
            return;
        }
        stringRedisTemplate.opsForValue().set(key, value, expire, TimeUnit.MILLISECONDS);
    }

    public String getValue(String key){
        return stringRedisTemplate.opsForValue().get(key);
    }
}
