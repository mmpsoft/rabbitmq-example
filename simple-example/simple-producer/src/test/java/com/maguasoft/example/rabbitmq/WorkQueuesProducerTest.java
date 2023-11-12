package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.workqueues.WorkQueuesProducer;
import org.junit.Test;

public class WorkQueuesProducerTest {

    private final WorkQueuesProducer producer = new WorkQueuesProducer();

    @Test
    public void testSendMessage() {
        producer.sendMessage();
    }
}
