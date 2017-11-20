package com.dmxiaoshen;

import com.dmxiaoshen.mail.SimpleMailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMailApplicationTests {

	@Autowired
	private SimpleMailSender simpleMailSender;

	@Test
	public void testSimpleMailSender() {
		simpleMailSender.send();
	}

}
