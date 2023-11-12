package com.maguasoft.example.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SimpleConsumer {

    @RabbitListener(queues = "springboot.queue")
    public void onMessage(String message) {
        log.info("SimpleConsumer onMessage: {}", message);
    }
}
