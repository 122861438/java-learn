package com.cx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;


@Service
public class Consumer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) throws JMSException {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");

        Consumer consumer = (Consumer) ctx.getBean("consumer");

//        while (true){
//            TextMessage message = (TextMessage)consumer.jmsTemplate.receive();
//            if(message!=null){
//                System.out.println(message.getText());
//            }else {
//                break;
//            }
//        }

        String str = (String)consumer.jmsTemplate.receiveAndConvert();
        System.out.println(str);
    }

}
