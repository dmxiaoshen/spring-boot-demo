package com.dmxiaoshen;

import com.dmxiaoshen.redis.StringRedisTemplateDemo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {

	@Autowired
	private StringRedisTemplateDemo stringRedisTemplateDemo;

	@Test
	public void testStringRedisTemplateDemo(){
		stringRedisTemplateDemo.setValue("aaa","mail",-1);
		Assert.assertEquals("mail",stringRedisTemplateDemo.getValue("aaa"));

		stringRedisTemplateDemo.setValue("bbb","weixin",5000);
		Assert.assertEquals("weixin",stringRedisTemplateDemo.getValue("bbb"));

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Assert.assertNull(stringRedisTemplateDemo.getValue("bbb"));

	}

}
