package com.taylor.hello.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class PTPProducer {
    public static void main(String[] args) {
        //连接信息设置
        String username = "user";
        String password = "user";
        String brokerURL = "failover://tcp://localhost:61616";
        //连接工厂
        ConnectionFactory connectionFactory = null;
        //连接对象
        Connection connection = null;
        //会话 接受或者发送消息的线程
        Session session = null;
        //消息的目的地
        Destination destination = null;
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
            /**
             * paramA:是否支持事务;
             *      如果为true，则会忽略paramB，paramB被jms服务器设置为SESSION_TRANSACTED
             *      如果为false,paramB的值可为；
             *          Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
             *          Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
             *          DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
             */
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

            //创建一个名称为QueueTest的消息队列
            destination = session.createQueue("QueueTest");
            //创建消息生产者
            messageProducer = session.createProducer(destination);

            //发送消息
            TextMessage message = null;
            for (int i = 0; i < 10; i++) {
                //创建要发送的文本信息
                message = session.createTextMessage("Queue消息测试-" + (i + 1));
                //通过消息生产者发出消息
                messageProducer.send(message);
                System.out.println("发送成功：" + message.getText());
            }
            session.commit();   //提交session
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != connection) {
                try {
                    connection.close();     //关闭连接
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
