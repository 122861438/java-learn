package com.sz.cx;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class Topic_Consumer_Persist {


    private static String URL = "tcp://39.97.246.154:61616";
    private static String TOPIC_NAME="topic_persist";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("***ls***");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("ls");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);

        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic,"remark....");
        connection.start();

        topicSubscriber.setMessageListener(message->{
            if(message!=null && message instanceof TextMessage){
                try {
                    System.out.println("messageLinster "+((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();
        topicSubscriber.close();
        session.close();
        connection.close();

    }
}
