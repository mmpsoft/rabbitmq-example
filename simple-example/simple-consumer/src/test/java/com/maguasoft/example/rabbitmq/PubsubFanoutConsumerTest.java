package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.pubsub.fanout.PubsubFanoutConsumer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class PubsubFanoutConsumerTest {

    private final PubsubFanoutConsumer consumer1 = new PubsubFanoutConsumer("消费者1", "pubsub.fanout.queue1");
    private final PubsubFanoutConsumer consumer2 = new PubsubFanoutConsumer("消费者2", "pubsub.fanout.queue2");

    @Test
    public void testOnMessage() throws Exception {
        // 开启多个消费者
        consumer1.onMessage();
        consumer2.onMessage();

        new CountDownLatch(1).await();
    }
}
