package com.dmxiaoshen.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;

/**
 * Created by hzhsg on 2017/11/14.
 */
@Configuration
public class RabbitMultiConfig {

    /**
     * 默认rabbitmq数据源
     * @param host
     * @param port
     * @param username
     * @param password
     * @return
     */
    @Bean("defaultConnectionFactory")
    @Primary
    public ConnectionFactory defaultConnectionFactory(@Value("${spring.rabbitmq.host}") String host,
                                                      @Value("${spring.rabbitmq.port}") Integer port,
                                                      @Value("${spring.rabbitmq.username}") String username,
                                                      @Value("${spring.rabbitmq.password}") String password) {
        return getConnectionFactory(host, port, username, password);
    }


    /**
     * 默认数据源的发送模版
     * @param connectionFactory
     * @return
     */
    @Bean(name = "defaultRabbitTemplate")
    public RabbitTemplate defaultRabbitTemplate(
            @Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory
    ) {
        return new RabbitTemplate(connectionFactory);
    }

    /**
     * 默认数据源的接收factory
     * @param configurer
     * @param connectionFactory
     * @return
     */
    @Bean(name = "defaultFactory")
    public SimpleRabbitListenerContainerFactory defaultFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("defaultConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }


    /**
     * 第二个rabbitmq数据源
     * @param host
     * @param port
     * @param username
     * @param password
     * @return
     */
    @Bean("secondConnectionFactory")
    public ConnectionFactory secondConnectionFactory(@Value("${spring-ext.rabbitmq.second.host}") String host,
                                                     @Value("${spring-ext.rabbitmq.second.port}") Integer port,
                                                     @Value("${spring-ext.rabbitmq.second.username}") String username,
                                                     @Value("${spring-ext.rabbitmq.second.password}") String password) {
        return getConnectionFactory(host, port, username, password);
    }

    /**
     * 第二个数据源的发送模版
     * @param connectionFactory
     * @return
     */
    @Bean(name = "secondRabbitTemplate")
    public RabbitTemplate secondRabbitTemplate(
            @Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory
    ) {
        return new RabbitTemplate(connectionFactory);
    }


    /**
     * 第二个数据源的接收factory
     * @param configurer
     * @param connectionFactory
     * @return
     */
    @Bean(name = "secondFactory")
    public SimpleRabbitListenerContainerFactory secondFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            @Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    @Bean
    public String helloQueue(@Qualifier("defaultConnectionFactory")ConnectionFactory connectionFactory){
        try {
            // 第二个参数 ture 为durable 永不丢失
            connectionFactory.createConnection().createChannel(false).queueDeclare("multi.hello", true, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return "hello";
        }
    }

    @Bean
    public String cardQueue(@Qualifier("secondConnectionFactory")ConnectionFactory connectionFactory){
        try {
            // 第二个参数 ture 为durable 永不丢失
            connectionFactory.createConnection().createChannel(false).queueDeclare("multi.card", true, false, false, null);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return "card";
        }
    }


    private CachingConnectionFactory getConnectionFactory(String host, Integer port, String username, String password) {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;
    }
}
