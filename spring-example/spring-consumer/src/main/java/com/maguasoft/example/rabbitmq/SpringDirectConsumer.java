package com.maguasoft.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringDirectConsumer {

    @RabbitListener(queues = "spring.direct.queue1")
    public void onSpringDirectQueue1Message(String message) {
        log.info("SpringDirectListener-消费者1: {}", message);
    }

    @RabbitListener(queues = "spring.direct.queue2")
    public void onSpringDirectQueue2Message(String message) {
        log.info("SpringDirectListener-消费者2: {}", message);
    }
}
