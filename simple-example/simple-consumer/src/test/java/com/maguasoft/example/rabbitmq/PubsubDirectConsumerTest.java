package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.pubsub.direct.PubsubDirectConsumer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class PubsubDirectConsumerTest {

    private final PubsubDirectConsumer consumer1 = new PubsubDirectConsumer("消费者1", "pubsub.direct.queue1");
    private final PubsubDirectConsumer consumer2 = new PubsubDirectConsumer("消费者2", "pubsub.direct.queue2");

    @Test
    public void testOnMessage() throws Exception {
        // 开启多个消费者
        consumer1.onMessage();
        consumer2.onMessage();

        new CountDownLatch(1).await();
    }
}
