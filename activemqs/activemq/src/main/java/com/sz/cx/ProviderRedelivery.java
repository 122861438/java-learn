package com.sz.cx;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ProviderRedelivery {

    private static String URL = "tcp://39.97.246.154:61616";
    private static String NAME="queue_redeelivery";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //设置为true,如果不提交，那么消息将不会发送到消息队列
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(NAME);
        MessageProducer producer = session.createProducer(queue);
        //设置持久化 默认就是持久化操作
        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
     //   producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        for (int i=1;i<=3;i++){
            TextMessage textMessage =  session.createTextMessage("msg_"+i);
            producer.send(textMessage);
        }
        producer.close();
      //  session.commit();
        session.close();
        connection.close();
        System.out.println("发送到mq队列了");
    }
}
