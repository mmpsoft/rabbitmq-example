package com.maguasoft.example.rabbitmq.ttl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringMessageTTLProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        MessagePostProcessor messagePostProcessor = message -> {
            // 设置消息的过期时间为 3s
            message.getMessageProperties().setExpiration("3000");
            return message;
        };

        // 发送消息
        rabbitTemplate.convertAndSend("spring.ttl.exchange", "spring.ttl",
                "Hello Spring TTL RabbitMQ-info", messagePostProcessor);
    }
}
