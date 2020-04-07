package com.cx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TopicProduceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TopicProduceApplication.class, args);
	}

}
