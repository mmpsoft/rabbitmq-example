package com.maguasoft.example.rabbitmq;

import org.junit.Test;

public class SimpleProducerTest {

    private final SimpleProducer producer = new SimpleProducer();

    @Test
    public void testSendMessage() {
        producer.sendMessage();
    }
}
