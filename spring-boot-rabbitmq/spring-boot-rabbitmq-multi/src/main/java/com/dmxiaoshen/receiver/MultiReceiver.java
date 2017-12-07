package com.dmxiaoshen.receiver;

import com.dmxiaoshen.sender.MultiHelloSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hzhsg on 2017/11/14.
 */
@Component
public class MultiReceiver {
    @Autowired
    private MultiHelloSender multiHelloSender;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = "multi.hello",containerFactory = "defaultFactory")
    public void processMultiHello(String json){
        logger.info("multi.hello queue process message:{}",json);
        //multiHelloSender.sender(json);
    }

    @RabbitListener(queues = "multi.card",containerFactory = "secondFactory")
    public void processMultiCard(String json){
        logger.info("multi.card queue process message:{}",json);
    }
}
