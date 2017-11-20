package com.dmxiaoshen;

import com.dmxiaoshen.dao.UserRepository;
import com.dmxiaoshen.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootJpaApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testUserRepository() {
		User u = new User();
		u.setName("AAA");
		u.setAge(23);
		u.setMobilePhone("13867465643");
		userRepository.save(u);

		User u2 = new User();
		u2.setName("BBB");
		u2.setAge(25);
		u2.setMobilePhone("15067465643");
		userRepository.save(u2);

		User aaa = userRepository.findByName("AAA");
		Assert.assertEquals(aaa.getId(),u.getId());

		User bbb = userRepository.findByNameAndAge("AAA",23);
		Assert.assertEquals(bbb.getId(),u.getId());

		User ccc = userRepository.findUser("BBB");
		Assert.assertEquals(ccc.getId(),u2.getId());
	}

}
