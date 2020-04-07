package com.sz.cx;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;

import javax.jms.*;
import java.io.IOException;

public class ConsumerRedelivery {

    private static String URL = "nio://39.97.246.154:61618";
    private static String NAME="queue_redeelivery";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("***二号消费者***");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
        RedeliveryPolicy queuePolicy = new RedeliveryPolicy();
        queuePolicy.setRedeliveryDelay(1000);
        queuePolicy.setMaximumRedeliveries(2);
        activeMQConnectionFactory.setRedeliveryPolicy(queuePolicy);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //如果设置为true,但是不提交，那么消息就是被消费，mq还会当作你没有消费
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener((message)->{
            if(message!=null && message instanceof TextMessage){
                try {
                    TextMessage textMessage = (TextMessage)message;
                    System.out.println("messageLinster "+(textMessage.getText()));
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
   //     session.commit();

        consumer.close();

        session.close();
        connection.close();

    }
}
