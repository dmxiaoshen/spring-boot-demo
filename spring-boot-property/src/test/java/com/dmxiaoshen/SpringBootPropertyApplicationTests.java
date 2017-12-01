package com.dmxiaoshen;

import com.dmxiaoshen.service.HelloService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SpringBootPropertyApplicationTests extends BaseControllerTest {

    private static final String URL = "http://localhost:8082/";

    @Test
    public void testSimple() {
        ResponseEntity entity = testRestTemplate.getForEntity(URL + "hello/simple", String.class);
        Assert.assertEquals(200, entity.getStatusCode().value());
        Assert.assertEquals("Lucus-12-上海-Lucus已经12岁，住在上海", entity.getBody());
    }

    @Test
    public void testHelloService(){
        HelloService.call("1","laocao");
        HelloService.call("2","xiaoyan",null);
        HelloService.call("3","luyi","");
        HelloService.call("3","qiniu","kaka");
    }

}
