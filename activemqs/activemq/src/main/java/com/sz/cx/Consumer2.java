package com.sz.cx;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class Consumer2 {

    private static String URL = "tcp://39.97.246.154:61616";
    private static String NAME="queue01";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("***二号消费者***");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener((message)->{
            if(message!=null && message instanceof TextMessage){
                try {
                    TextMessage textMessage = (TextMessage)message;
                    System.out.println("messageLinster "+(textMessage.getText()+" : "+textMessage.getStringProperty("vip")));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }

            if(message!=null && message instanceof MapMessage){
                try {
                    MapMessage mapMessage = (MapMessage)message;
                    System.out.println("messageLinster "+mapMessage.getString("k1"));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //使用监听器的方法是异步的，这里会执行到
//        for (int i=0;i<1000;i++){
//            System.out.println("aaaa");
//        }
        System.in.read();
        consumer.close();
        session.close();
        connection.close();

    }
}
