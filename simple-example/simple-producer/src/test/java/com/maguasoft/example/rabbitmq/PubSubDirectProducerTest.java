package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.pubsub.direct.PubSubDirectProducer;
import org.junit.Test;

public class PubSubDirectProducerTest {
    private final PubSubDirectProducer producer = new PubSubDirectProducer();

    @Test
    public void testSendMessage() {
        producer.sendMessage();
    }
}
