package com.taylor.helloredis;

import com.taylor.helloredis.pojo.User;
import com.taylor.helloredis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
@SpringBootApplication
public class HelloRedisApplication {

    @Autowired
    private RedisService redisService;

    @PostConstruct
    public void init(){
        redisService.setObj("hello",new User("taylor",22));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println( redisService.getObj("hello"));
    }
    public static void main(String[] args) {
        SpringApplication.run(HelloRedisApplication.class, args);
    }

}
