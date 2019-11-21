package com.taylor.hellokafka;

import com.taylor.hellokafka.producer.UserLogProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class HelloKafkaApplication {

    @Autowired
    private UserLogProducer userLogProducer;

    /**
     * @PostConstruct  注解一般用作初始化执行的函数。
     */
    @PostConstruct
    public void init(){
        for (int i = 20; i < 25; i++) {
            userLogProducer.sendLog(String.valueOf(i));
        }
    }
    public static void main(String[] args) {

        SpringApplication.run(HelloKafkaApplication.class, args);
    }
}
