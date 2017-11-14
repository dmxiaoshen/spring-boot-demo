package com.dmxiaoshen.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by hzhsg on 2017/11/14.
 */
@Component
public class MultiReceiver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = "multi.hello",containerFactory = "defaultFactory")
    public void processMultiHello(String json){
        logger.info("multi.hello queue process message:{}",json);
    }

    @RabbitListener(queues = "multi.card",containerFactory = "secondFactory")
    public void processMultiCard(String json){
        logger.info("multi.card queue process message:{}",json);
    }
}
