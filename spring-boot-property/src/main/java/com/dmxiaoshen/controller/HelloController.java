package com.dmxiaoshen.controller;

import com.dmxiaoshen.config.RandomProperties;
import com.dmxiaoshen.config.StaticProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hzhsg on 2017/11/9.
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Value("${value.name}")
    private String name;
    @Value("${value.age}")
    private int age;
    @Value("${value.address}")
    private String address;
    @Value("${value.description}")
    private String description;

    @Autowired
    private RandomProperties randomProperties;

    @GetMapping("/simple")
    public String getPropertySimple(){
        return name+"-"+age+"-"+address+"-"+description;
    }

    @GetMapping("/random")
    public String getRandomProperties(){
        return randomProperties.toString();
    }

    @GetMapping("/static")
    public String getStaticProperties(){
        return StaticProperties.APP_ID+"-"+StaticProperties.APP_NAME;
    }
}
