package com.dmxiaoshen;

import com.dmxiaoshen.sender.MultiCardSender;
import com.dmxiaoshen.sender.MultiHelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitmqMultiApplicationTests {

	@Autowired
	private MultiHelloSender multiHelloSender;
	@Autowired
	private MultiCardSender multiCardSender;


	@Test
	public void testMultiHelloSender(){
		for(int i=0;i<100;i++){
			multiHelloSender.sender("{\"title\":\"multi.hello\"}");
		}
	}

	@Test
	public void testMultiCardSender(){
		for(int i=0;i<100;i++){
			multiCardSender.sender("{\"title\":\"multi.card\"}");
		}
	}

}
