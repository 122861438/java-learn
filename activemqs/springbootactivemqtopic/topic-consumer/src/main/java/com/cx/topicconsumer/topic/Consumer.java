package com.cx.topicconsumer.topic;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class Consumer {

    @JmsListener(destination = "boot-activemq-topic")
    public void receive(TextMessage textMessage){
        try {
            System.out.println(textMessage.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
