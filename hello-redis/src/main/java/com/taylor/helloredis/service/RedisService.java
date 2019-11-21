package com.taylor.helloredis.service;

import com.taylor.helloredis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    public void setObj(String key, User value) {
        redisTemplate.opsForValue().set(key,value);
    }

    public User getObj(String key) {
        return (User)redisTemplate.opsForValue().get(key);
    }
}