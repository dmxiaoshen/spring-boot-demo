package com.dmxiaoshen.controller;

import com.dmxiaoshen.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * Created by hzhsg on 2017/11/8.
 */
@RestController
@RequestMapping("/hello")
public class HelloWorldController {

    @GetMapping("/{name}")
    public String hello(@PathVariable("name")String name){
        return "hello "+name+" !";
    }

    @GetMapping("/test")
    public Book book(){
        Book book = new Book();
        book.setFeeId("65765431");
        book.setFqMoney(new BigDecimal("33.9"));
        return book;
    }
}
