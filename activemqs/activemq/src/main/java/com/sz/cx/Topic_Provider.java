package com.sz.cx;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Topic_Provider {

    private static String URL = "tcp://39.97.246.154:61616";
    private static String TOPIC_NAME="topic01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        for (int i=1;i<=6;i++){
            TextMessage textMessage =  session.createTextMessage("msg_"+i);
            producer.send(textMessage);
        }
        producer.close();
        session.close();
        connection.close();
        System.out.println("发送到mq的topic了");
    }

}
