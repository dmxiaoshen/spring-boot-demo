package com.dmxiaoshen.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by hzhsg on 2017/11/14.
 */
@Component
public class MultiHelloSender {

    @Resource(name = "defaultRabbitTemplate")
    private RabbitTemplate defaultRabbitTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public void sender(String json) {
        defaultRabbitTemplate.setChannelTransacted(false);
        threadLocal.set(1);
        if (!defaultRabbitTemplate.isConfirmListener()) {
            defaultRabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
                @Override
                public void confirm(CorrelationData correlationData, boolean ack, String s) {
                    if (!ack) {
                        int count = threadLocal.get();
                        if (count <= 3) {
                            threadLocal.set(count++);
                            defaultRabbitTemplate.convertAndSend("hello-exchange","hello-routekey",json);
                        } else {
                            logger.error("multi.hello 发送3次失败");
                        }
                    }else{
                        logger.info("multi.hello ack!");
                    }
                }
            });
        }
        defaultRabbitTemplate.convertAndSend("hello-exchange","hello-routekey",json);
    }
}
