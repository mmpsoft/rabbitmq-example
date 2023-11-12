package com.maguasoft.example.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringFanoutProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        for (int index = 0; index < 50; index++) {
            rabbitTemplate.convertAndSend("spring.fanout.exchange", "", "Hello Spring Fanout RabbitMQ-" + index);
        }
    }
}
