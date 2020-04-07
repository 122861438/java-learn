package com.cx.topicconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
public class TopicConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopicConsumerApplication.class, args);
	}

}
