package com.maguasoft.example.rabbitmq.dlx;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringDLXProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        // 发送消息
        rabbitTemplate.convertAndSend("spring.general.exchange", "general.info", "Hello Spring DLX RabbitMQ-info");
    }
}
