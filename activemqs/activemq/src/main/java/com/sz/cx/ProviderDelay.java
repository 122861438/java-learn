package com.sz.cx;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

import javax.jms.*;
import java.io.IOException;

public class ProviderDelay {

    private static String URL = "tcp://39.97.246.154:61616";
    private static String NAME="queue_delay";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(URL);
        activeMQConnectionFactory.setUseAsyncSend(true);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = session.createQueue(NAME);
        MessageProducer producer = session.createProducer(queue);

        long delay = 5*1000;
        long period=1*1000;
        int repeat=5;
        String cron = "*/5 * * * * ";
        String cron1= "0 * * * *";

//        for (int i=1;i<=2;i++){
            TextMessage textMessage =  session.createTextMessage("msg_");
//            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delay);
//            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,period);
//            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,repeat);
            textMessage.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON,cron);
            producer.send(textMessage);
//        }
        System.in.read();
        producer.close();
      //  session.commit();
        session.close();
        connection.close();
        System.out.println("发送到mq队列了");
    }
}
