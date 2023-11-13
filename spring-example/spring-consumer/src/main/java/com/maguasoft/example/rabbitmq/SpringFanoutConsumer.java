package com.maguasoft.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringFanoutConsumer {

    @RabbitListener(queues = "spring.fanout.queue1")
    public void onSpringFanoutQueue1Message(String message) {
        log.info("SpringFanoutListener-消费者1: {}", message);
    }

    @RabbitListener(queues = "spring.fanout.queue2")
    public void onSpringFanoutQueue2Message(String message) {
        log.info("SpringFanoutListener-消费者2: {}", message);
    }
}
