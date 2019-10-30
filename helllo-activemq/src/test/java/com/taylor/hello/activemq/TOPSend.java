package com.taylor.hello.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class TOPSend {
    public static void main(String[] args) {
        String username = "user";
        String password = "user";
        String brokerURL = "failover://tcp://localhost:61616";
        //连接工厂
        ConnectionFactory connectionFactory = null;
        //连接
        Connection connection = null;
        //会话 接受或者发送消息的线程
        Session session = null;
        //消息的主题
        Topic topic = null;
        //消息生产者
        MessageProducer messageProducer = null;
        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(username, password, brokerURL);
        try {
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            //=======================================================
            //点对点与发布-订阅模式唯一不同的地方，就是这一行代码，点对点创建的是Queue，而发布-订阅模式创建的是Topic
            //创建名为TopicTest的主题
            topic = session.createTopic("TopicTest");
            //=======================================================

            //创建主题生产者
            messageProducer = session.createProducer(topic);
            messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);//不将数据持久化
            //发送主题
            TextMessage message = null;
            for (int i = 0; i < 10; i++) {
                //创建要发送的文本信息
                message = session.createTextMessage("Topic主题测试" + (i + 1));
                //通过主题生产者发出消息
                messageProducer.send(message);
                System.out.println("发送成功：" + message.getText());
            }
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
