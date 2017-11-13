package com.dmxiaoshen.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by hzhsg on 2017/11/13.
 */
@Component
public class SimpleReceiver {

    private static final Logger logger = LoggerFactory.getLogger(SimpleReceiver.class);

    @RabbitListener(queues = "hello")
    @RabbitHandler
    public void processHello(String content){
        logger.info("receiver queue hello messag【{}】 from rabbitmq",content);
    }

    @RabbitListener(queues = "card")
    @RabbitHandler
    public void processCard(String content){
        logger.info("receiver queue card messag【{}】 from rabbitmq",content);
    }
}
