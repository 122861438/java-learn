package com.cx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;


import javax.jms.TextMessage;

@Service
public class Produce {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
        Produce produce = (Produce)ctx.getBean("produce");
        produce.jmsTemplate.send((session)->{
            TextMessage message = (TextMessage)session.createTextMessage("spring 整合 actiemq topic");
            return message;
        });
        System.out.println("send task off");
    }

}
