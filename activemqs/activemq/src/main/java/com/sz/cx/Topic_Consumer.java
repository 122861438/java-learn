package com.sz.cx;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class Topic_Consumer {


    private static String URL = "tcp://39.97.246.154:61616";
    private static String TOPIC_NAME="topic01";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("***二号消费者***");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageConsumer consumer = session.createConsumer(topic);
        consumer.setMessageListener(message->{
            if(message!=null && message instanceof TextMessage){
                try {
                    System.out.println("messageLinster "+((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();
        consumer.close();
        session.close();
        connection.close();

    }
}
