package com.dmxiaoshen;

import com.dmxiaoshen.common.JsonUtil;
import com.dmxiaoshen.entity.Book;
import com.dmxiaoshen.redis.StringRedisTemplateDemo;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

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

    @Test
    public void testJsonUtil(){
        LinkedList<Book> bookList = new LinkedList<>();
        bookList.add(new Book("java",new BigDecimal("44")));
        bookList.add(new Book("c++",new BigDecimal("41")));
        bookList.add(new Book("python",new BigDecimal("74")));

        stringRedisTemplateDemo.setValue("book-list", JsonUtil.toJson(bookList),-1);

        String result = stringRedisTemplateDemo.getValue("book-list");

        LinkedList<Book> k = JsonUtil.toLinkedList(result,Book.class);
        System.out.println(result);
    }

    @Test
    public void testJsonUtilList(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("java",new BigDecimal("44")));
        bookList.add(new Book("c++",new BigDecimal("41")));
        bookList.add(new Book("python",new BigDecimal("74")));

        stringRedisTemplateDemo.setValue("book-list-2", JsonUtil.toJson(bookList),-1);

        String result = stringRedisTemplateDemo.getValue("book-list-2");

        List<Book> k = JsonUtil.toList(result,Book.class);
        System.out.println(result);
    }

    @Test
    public void testJsonUtilMap(){
       Map<Integer,Book> map = new HashMap<>();
        map.put(1,new Book("java",new BigDecimal("44")));
        map.put(2,new Book("c++",new BigDecimal("41")));
        map.put(4,new Book("python",new BigDecimal("74")));

        stringRedisTemplateDemo.setValue("book-map", JsonUtil.toJson(map),-1);

        String result = stringRedisTemplateDemo.getValue("book-map");

        Map<Integer,Book> k = JsonUtil.toMap(result,Integer.class,Book.class);
        System.out.println(result);
    }

    @Test
    public void testBookLinkedList(){
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("java",new BigDecimal("44")));
        bookList.add(new Book("c++",new BigDecimal("41")));
        bookList.add(new Book("python",new BigDecimal("74")));
        LinkedList<Book> list = new LinkedList<>(bookList);

        Book temp = null;
        for(int i = 0;i<list.size();i++){
            temp = list.pollFirst();

            list.addLast(temp);
            System.out.println("poll-"+i);
        }
    }

}
