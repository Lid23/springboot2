package com.noodles.springbootrabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: springboot2
 * @description: Rabbitmq配置类
 * @author: Eric
 * @create: 2019-05-08 15:28
 **/

@Configuration
public class RabbitConfig {

    public static final String DEFAULT_BOOK_QUEUE = "dev.book.register.default.queue";
    public static final String MANUAL_BOOK_QUEUE = "dev.book.register.manual.queue";

    @Bean
    public Queue defaultBookQueue(){
        //第一个参数是QUEUE的名字，第二个参数是消息是否需要持久化处理
        return new Queue(DEFAULT_BOOK_QUEUE, true);
    }

    @Bean
    public Queue manualBookQueue(){
        //第一个参数是QUEUE的名字，第二个参数是消息是否需要持久化处理
        return new Queue(MANUAL_BOOK_QUEUE, true);
    }

}
