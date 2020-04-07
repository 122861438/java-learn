package com.sz.cx;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.UUID;

public class Provider {

    private static String URL = "tcp://39.97.246.154:61616";
    private static String NAME="queue01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(NAME);
        MessageProducer producer = session.createProducer(queue);
        for (int i=1;i<=3;i++){
            TextMessage textMessage =  session.createTextMessage("msg_"+i);
            textMessage.setStringProperty("vip","I'am VIP");
            producer.send(textMessage);

            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("k1","haha");
            producer.send(mapMessage);
        }
        producer.close();
        session.close();
        connection.close();
        System.out.println("发送到mq队列了");
    }

}
