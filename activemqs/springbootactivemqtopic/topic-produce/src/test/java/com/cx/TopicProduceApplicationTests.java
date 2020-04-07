package com.cx;

import com.cx.topicconsumer.topic.Produce;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TopicProduceApplicationTests {

	@Autowired
	Produce produce;

	@Test
	void contextLoads() {
		produce.prduceMsgTopic();
	}

}
