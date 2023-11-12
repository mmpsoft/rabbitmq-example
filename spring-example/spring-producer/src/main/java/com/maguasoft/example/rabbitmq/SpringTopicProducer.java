package com.maguasoft.example.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringTopicProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        rabbitTemplate.convertAndSend("spring.topic.exchange", "test.orange.example", "Hello Spring Topic RabbitMQ | test.orange.example");
        rabbitTemplate.convertAndSend("spring.topic.exchange", "test.example.rabbit", "Hello Spring Topic RabbitMQ | test.example.rabbit");
        rabbitTemplate.convertAndSend("spring.topic.exchange", "Lazy.test", "Hello Spring Topic RabbitMQ | Lazy.test");
        rabbitTemplate.convertAndSend("spring.topic.exchange", "Lazy.test.example", "Hello Spring Topic RabbitMQ | Lazy.test.example");
    }
}
