package com.dmxiaoshen;

import com.dmxiaoshen.dao.StudentDao;
import com.dmxiaoshen.dao.UserDao;
import com.dmxiaoshen.entity.Student;
import com.dmxiaoshen.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMybatisApplicationTests {

    @Autowired
    private UserDao userDao;
    @Autowired
    private StudentDao studentDao;

    @Test
    public void testUserDao() {
        User u = userDao.selectByPrimaryKey("40288fd15fd86ed8015fd86ee3ef0000");
        Assert.assertNotNull(u);
    }

    @Test
    public void testStudentDao() {
        Student student = studentDao.findByName("小李");
        Assert.assertNotNull(student);
    }

}
