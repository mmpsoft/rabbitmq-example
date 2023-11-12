package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.simple.SimpleConsumer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SimpleConsumerTest {

    private final SimpleConsumer consumer = new SimpleConsumer();

    @Test
    public void testOnMessage() throws Exception {
        log.info("test on message");
        consumer.onMessage();
    }
}
