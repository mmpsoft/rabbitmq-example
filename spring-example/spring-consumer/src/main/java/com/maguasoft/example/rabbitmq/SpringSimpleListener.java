package com.maguasoft.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringSimpleListener {

    @RabbitListener(queues = "spring.simple.queue")
    public void onMessage(String message) {
        log.info("SpringSimpleListener-消费者1: {}", message);
    }
}
