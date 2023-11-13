package com.maguasoft.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringQueueTTLProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        // 发送消息
        rabbitTemplate.convertAndSend("spring.ttl.exchange", "spring.ttl", "Hello Spring TTL RabbitMQ-info");
    }
}
