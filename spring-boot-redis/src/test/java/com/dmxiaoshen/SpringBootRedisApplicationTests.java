package com.dmxiaoshen;

import com.dmxiaoshen.common.JsonUtil;
import com.dmxiaoshen.redis.StringRedisTemplateDemo;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {

    @Autowired
    private StringRedisTemplateDemo stringRedisTemplateDemo;

    @Test
    public void testStringRedisTemplateDemo() {
        stringRedisTemplateDemo.setValue("aaa", "mail", -1);
        Assert.assertEquals("mail", stringRedisTemplateDemo.getValue("aaa"));

        stringRedisTemplateDemo.setValue("bbb", "weixin", 5000);
        Assert.assertEquals("weixin", stringRedisTemplateDemo.getValue("bbb"));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertNull(stringRedisTemplateDemo.getValue("bbb"));

    }

    @Test
    public void testList() {
        List<String> stringList = new ArrayList<>();
        stringList.add("AAA");
        stringList.add("BBB");
        stringList.add("CCC");
        stringRedisTemplateDemo.setValue("k1-1", JsonUtil.toJson(stringList),-1);

        String result = stringRedisTemplateDemo.getValue("k1-1");

        JsonNode node = JsonUtil.toJsonNode(result);

        List<String> k = JsonUtil.toList(result,String.class);
    }

    @Test
    public void testLinkedList(){
        LinkedList<String> list = new LinkedList<>();
        list.add("AAA");
        list.add("BBB");
        list.add("CCC");
        stringRedisTemplateDemo.setValue("k1-2", JsonUtil.toJson(list),-1);

        String result = stringRedisTemplateDemo.getValue("k1-2");

        JsonNode node = JsonUtil.toJsonNode(result);

        LinkedList<String> k = JsonUtil.toLinkedList(result,String.class);
        String first = k.pollFirst();
        k.addLast(first);

        int i = 0;
        int size = k.size();
        while (i<size){
            i++;
            k.addLast(k.pollFirst());
        }

        System.out.println(JsonUtil.toJson(k));
    }

}
