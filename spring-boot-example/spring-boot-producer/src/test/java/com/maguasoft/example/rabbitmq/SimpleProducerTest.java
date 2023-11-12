package com.maguasoft.example.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SimpleProducerTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleSendMessage() {
        rabbitTemplate.convertAndSend("springboot.queue", "Hello RabbitMQ!");
    }
}
