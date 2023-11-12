package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.workqueues.WorkQueuesConsumer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class WorkQueuesConsumerTest {

    private final WorkQueuesConsumer consumer1 = new WorkQueuesConsumer("消费者1");
    private final WorkQueuesConsumer consumer2 = new WorkQueuesConsumer("消费者2");
    private final WorkQueuesConsumer consumer3 = new WorkQueuesConsumer("消费者3");

    @Test
    public void testOnMessage() throws Exception {
        // 开启多个消费者
        consumer1.onMessage();
        consumer2.onMessage();
        consumer3.onMessage();

        new CountDownLatch(1).await();
    }
}
