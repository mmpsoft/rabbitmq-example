package com.maguasoft.example.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringSimpleProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        rabbitTemplate.convertAndSend("spring.simple.queue", "Hello Spring Simple RabbitMQ");
    }
}
