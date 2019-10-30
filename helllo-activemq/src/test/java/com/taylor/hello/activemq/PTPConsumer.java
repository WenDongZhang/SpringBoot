package com.taylor.hello.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PTPConsumer {
    public static void main(String[] args) {
        //连接信息设置
        String username = "user";
        String password = "user";
        String brokerURL = "failover://tcp://localhost:61616";
        //连接工厂
        ConnectionFactory connectionFactory = null;
        //连接
        Connection connection = null;
        //会话 接受或者发送消息的线程
        Session session = null;
        //消息的目的地
        Destination destination = null;
        //消息消费者
        MessageConsumer messageConsumer = null;
        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);

        try {
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //=======================================================
            //点对点与发布-订阅模式唯一不同的地方，就是这一行代码，点对点创建的是Queue，而发布-订阅模式创建的是Topic
            //创建名为QueueTest的队列
            destination = session.createQueue("QueueTest");
            //=======================================================

            //创建一个连接QueueTest的消息队列

            //创建消息消费者
            messageConsumer = session.createConsumer(destination);


            //接收方法一：通过while (true)循环，给receive方法提供一个很大的long类型参数
            /*while (true) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive(100000);
                if(textMessage != null){
                    System.out.println("成功接收消息:" + textMessage.getText());
                }else {
                    break;
                }
            }*/

            //接收方法二：实现一个监听器后，以后只要有消息，就会通过这个监听器接收到
            messageConsumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        //获取到接收的数据
                        String textMessage = ((TextMessage) message).getText();
                        System.out.println("成功接收消息:" + textMessage);
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
