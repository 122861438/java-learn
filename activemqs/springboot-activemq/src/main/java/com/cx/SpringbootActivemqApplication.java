package com.cx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootActivemqApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootActivemqApplication.class, args);
	}

}
