package com.taylor.hellokafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @Author zhangwendong5 2019/9/23 14:05
 * @Version
 **/

@Component
public class TestConsumer2 {

    //使用监听器注解实现对指定的条件进行消息的监听
    @KafkaListener(topics = {"test"})
    public void consumer(ConsumerRecord<?,?> consumerRecord) throws InterruptedException {
        //判断是否为null
        Optional<?> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if(kafkaMessage.isPresent()){
            //得到Optional实例中的值
            Object message = kafkaMessage.get();
            System.out.println("消费数据"+message);
        }
    }

}
