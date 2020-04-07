package com.sz.cx;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

public class ConsumerA {

    private static String URL = "nio://39.97.246.154:61618";
    private static String NAME="queue_delay";

    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("***二号消费者***");
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //如果设置为true,但是不提交，那么消息就是被消费，mq还会当作你没有消费
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(NAME);
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener((message)->{
            if(message!=null && message instanceof TextMessage){
                try {
                    TextMessage textMessage = (TextMessage)message;
                    //设置手动签收需要调用acknowledge方法，才算消费完成
                    //如果事务是开启的，那么即使不用这个，事务提交了也算被消费
                   // textMessage.acknowledge();
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
