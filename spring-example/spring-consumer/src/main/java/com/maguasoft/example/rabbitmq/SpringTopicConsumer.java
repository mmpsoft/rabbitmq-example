package com.maguasoft.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringTopicConsumer {

    @RabbitListener(queues = "spring.topic.queue1")
    public void onSpringTopicQueue1Message(String message) {
        log.info("SpringTopicListener-消费者1: {}", message);
    }

    @RabbitListener(queues = "spring.topic.queue2")
    public void onSpringTopicQueue2Message(String message) {
        log.info("SpringTopicListener-消费者2: {}", message);
    }
}
