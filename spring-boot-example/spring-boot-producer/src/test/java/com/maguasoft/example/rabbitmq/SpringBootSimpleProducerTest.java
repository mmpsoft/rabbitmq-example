package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.producer.SpringBootSimpleProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootSimpleProducerTest {

    @Autowired
    private SpringBootSimpleProducer producer;

    @Test
    public void sendMessage() {
        producer.sendMessage();
    }
}
