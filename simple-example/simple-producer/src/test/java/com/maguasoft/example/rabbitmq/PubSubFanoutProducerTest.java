package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.pubsub.fanout.PubSubFanoutProducer;
import org.junit.Test;

public class PubSubFanoutProducerTest {
    private final PubSubFanoutProducer producer = new PubSubFanoutProducer();

    @Test
    public void testSendMessage() {
        producer.sendMessage();
    }
}
