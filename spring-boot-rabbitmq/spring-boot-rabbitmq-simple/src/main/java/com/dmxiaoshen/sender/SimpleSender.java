package com.dmxiaoshen.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hzhsg on 2017/11/13.
 */
@Component
public class SimpleSender {

    private static final Logger logger = LoggerFactory.getLogger(SimpleSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendToQueueHello(String content){
        amqpTemplate.convertAndSend("hello",content);
        logger.info("send queue hello with message【{}】 to rabbitmq",content);
    }

    public void sendToQueueCard(String content){
        amqpTemplate.convertAndSend("card",content);
        logger.info("send queue card with message【{}】 to rabbitmq",content);
    }
}
