package com.taylor.hellokafka.producer;

import com.alibaba.fastjson.JSON;
import com.taylor.hellokafka.pojo.UserLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserLogProducer {
    /**
     * 注入kafkaTemplate
     */
    @Autowired
    private KafkaTemplate kafkaTemplate;
    /**
     * 发送数据
     * @param userid
     */
    public void sendLog(String userid){
        UserLog userLog = new UserLog();
        userLog.setUsername("taylor");
        userLog.setUserid(userid);
        userLog.setState("0");
        kafkaTemplate.send("user-log", JSON.toJSONString(userLog));
    }
}
