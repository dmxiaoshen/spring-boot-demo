package com.dmxiaoshen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
