package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.pubsub.topic.PubsubTopicConsumer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class PubsubTopicConsumerTest {

    private final PubsubTopicConsumer consumer1 = new PubsubTopicConsumer("消费者1", "pubsub.topic.queue1");
    private final PubsubTopicConsumer consumer2 = new PubsubTopicConsumer("消费者2", "pubsub.topic.queue2");

    @Test
    public void testOnMessage() throws Exception {
        // 开启多个消费者
        consumer1.onMessage();
        consumer2.onMessage();

        new CountDownLatch(1).await();
    }
}
