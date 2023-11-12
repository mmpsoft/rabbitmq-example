package com.maguasoft.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SimpleConsumerTest {

    private final SimpleConsumer consumer = new SimpleConsumer();

    @Test
    public void testOnMessage() {
        log.info("test on message");
        consumer.onMessage();
    }
}
