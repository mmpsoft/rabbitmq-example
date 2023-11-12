package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.producer.SpringBootFanoutProducer;
import com.maguasoft.example.rabbitmq.producer.SpringBootTopicProducer;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootTopicProducerTest {

    @Autowired
    private SpringBootTopicProducer producer;

    @Test
    public void sendMessage() {
        producer.sendMessage();
    }
}
