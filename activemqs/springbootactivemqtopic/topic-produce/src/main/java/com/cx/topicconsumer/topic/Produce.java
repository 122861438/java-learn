package com.cx.topicconsumer.topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Topic;
import java.util.UUID;

@Component
public class Produce {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic topic;

    @Scheduled(fixedDelay = 3000)
    public void prduceMsgTopic(){
        jmsMessagingTemplate.convertAndSend(topic,"**topic** : "+ UUID.randomUUID().toString().substring(0,6));
    }

}
