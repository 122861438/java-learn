package com.sz.cx;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;

import javax.jms.*;
import java.util.UUID;

public class ProviderB {

    private static String URL = "tcp://39.97.246.154:61616";
    private static String NAME="queue_async";
    //通过使用ActiveMQMessagePrducer的send方法的回调，确定是是否发送成功
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
        activeMQConnectionFactory.setUseAsyncSend(true);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        //设置为true,如果不提交，那么消息将不会发送到消息队列
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(NAME);
        ActiveMQMessageProducer activeMQMessageProducer = (ActiveMQMessageProducer)session.createProducer(queue);
        //设置持久化 默认就是持久化操作
        activeMQMessageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
     //   producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        for (int i=1;i<=3;i++){
            TextMessage textMessage =  session.createTextMessage("msg_"+i);
            textMessage.setJMSMessageID("async-"+ UUID.randomUUID().toString());
            String messageId = textMessage.getJMSMessageID();
            activeMQMessageProducer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    try {
                        System.out.println("success messageid: " + messageId + ", text:" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onException(JMSException exception) {
                    try {
                        System.out.println("error messageid: " + messageId + ", text:" + textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        activeMQMessageProducer.close();
      //  session.commit();
        session.close();
        connection.close();
        System.out.println("发送到mq队列了");
    }
}
