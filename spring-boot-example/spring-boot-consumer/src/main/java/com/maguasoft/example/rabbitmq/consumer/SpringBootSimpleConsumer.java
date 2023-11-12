package com.maguasoft.example.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringBootSimpleConsumer {

    @RabbitListener(queues = "springboot.simple.queue")
    public void onMessage(String message) {
        log.info("SpringBootSimpleConsumer onMessage: {}", message);
    }
}
