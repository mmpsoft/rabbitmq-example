package com.maguasoft.example.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
public class SpringBootConsumerTest {

    @Test
    public void testConsumer() throws InterruptedException {
        new CountDownLatch(1).await();
    }
}
