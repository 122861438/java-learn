package com.cx;

import com.cx.mq.QueueProduce;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootActivemqApplicationTests {

	@Autowired
	private QueueProduce queueProduce;

	@Test
	void contextLoads() {
		queueProduce.produceMsg();
	}

}
