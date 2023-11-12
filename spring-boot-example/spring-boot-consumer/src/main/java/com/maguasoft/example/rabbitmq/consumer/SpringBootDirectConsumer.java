package com.maguasoft.example.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringBootDirectConsumer {

    @RabbitListener(queues = "springboot.direct.queue1")
    public void onSpringBootDirectQueue1Message(String message) {
        log.info("SpringBootDirectConsumer 消费者1 onMessage: {}", message);
    }

    @RabbitListener(queues = "springboot.direct.queue2")
    public void onSpringBootDirectQueue2Message(String message) {
        log.info("SpringBootDirectConsumer 消费者2 onMessage: {}", message);
    }
}
