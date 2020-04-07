package com.sz.cx;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class Consumer {

    private static String URL = "tcp://39.97.246.154:61616";
    private static String NAME="queue01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(NAME);
        MessageConsumer consumer = session.createConsumer(queue);
//        new Thread(()->{
//            for (int i=0;i<1000;i++){
//                System.out.println("aaaa");
//            }
//        }).start();
        while (true){
            TextMessage receive = (TextMessage) consumer.receive();
            if (receive==null){
                break;
            }else {
                System.out.println("消费消息*******"+receive.getText());
            }
        }
        //使用recieve方法是同步的，这里不会执行到


        consumer.close();
        session.close();
        connection.close();
    }

}
