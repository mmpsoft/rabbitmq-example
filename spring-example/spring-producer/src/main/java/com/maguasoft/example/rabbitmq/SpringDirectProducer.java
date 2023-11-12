package com.maguasoft.example.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringDirectProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        rabbitTemplate.convertAndSend("spring.direct.exchange", "info", "Hello Spring Direct RabbitMQ-info");
        rabbitTemplate.convertAndSend("spring.direct.exchange", "error", "Hello Spring Direct RabbitMQ-error");
        rabbitTemplate.convertAndSend("spring.direct.exchange", "warn", "Hello Spring Direct RabbitMQ-warn");
    }
}
