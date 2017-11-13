package com.dmxiaoshen;

import com.dmxiaoshen.sender.SimpleSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRabbitmqSimpleApplicationTests {

    @Autowired
    private SimpleSender simpleSender;

    @Test
    public void testSendToQueueHello() {
        for (int i = 0; i < 100; i++) {
            simpleSender.sendToQueueHello("[hello]-" + i);
        }
    }

    @Test
    public void testSendToQueueCard() {
        for (int i = 0; i < 100; i++) {
            simpleSender.sendToQueueCard("[card]-" + i);
        }
    }

}
