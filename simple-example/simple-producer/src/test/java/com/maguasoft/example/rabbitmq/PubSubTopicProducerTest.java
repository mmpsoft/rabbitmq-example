package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.pubsub.topic.PubSubTopicProducer;
import org.junit.Test;

public class PubSubTopicProducerTest {
    private final PubSubTopicProducer producer = new PubSubTopicProducer();

    @Test
    public void testSendMessage() {
        producer.sendMessage();
    }
}
