package com.maguasoft.example.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringBootTopicConsumer {

    @RabbitListener(queues = "springboot.topic.queue1")
    public void onSpringBootTopicQueue1Message(String message) {
        log.info("SpringBootTopicConsumer 消费者1 onMessage: {}", message);
    }

    @RabbitListener(queues = "springboot.topic.queue2")
    public void onSpringBootTopicQueue2Message(String message) {
        log.info("SpringBootTopicConsumer 消费者2 onMessage: {}", message);
    }
}
