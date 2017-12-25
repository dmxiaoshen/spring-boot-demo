package com.dmxiaoshen.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by hzhsg on 2017/11/20.
 */
@Component
public class SimpleMailSender {

    @Autowired
    private JavaMailSender javaMailSender;


    public void send(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hsg@dmxiaoshen.com");
        message.setTo("dmxiaoshen@163.com");
        message.setSubject("简单邮件");
        message.setText("这是一封简单测试邮件");
        javaMailSender.send(message);
    }

    public static void main(String[] args) {
        Date d = new Date();
        System.out.println("hello");

    }
}
