package com.maguasoft.example.rabbitmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringBootFanoutConsumer {

    @RabbitListener(queues = "springboot.fanout.queue1")
    public void onSpringBootFanoutQueue1Message(String message) {
        log.info("SpringBootFanoutConsumer 消费者1 onMessage: {}", message);
    }

    @RabbitListener(queues = "springboot.fanout.queue2")
    public void onSpringBootFanoutQueue2Message(String message) {
        log.info("SpringBootFanoutConsumer 消费者2 onMessage: {}", message);
    }
}
