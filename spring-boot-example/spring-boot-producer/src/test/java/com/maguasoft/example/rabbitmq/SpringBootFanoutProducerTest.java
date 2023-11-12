package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.producer.SpringBootFanoutProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootFanoutProducerTest {

    @Autowired
    private SpringBootFanoutProducer producer;

    @Test
    public void sendMessage() {
        producer.sendMessage();
    }
}
