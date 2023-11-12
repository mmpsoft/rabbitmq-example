package com.maguasoft.example.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SimpleProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        // String exchange      交换机
        // String routingKey    路由的Key
        // Object message,      消息内容
        // MessagePostProcessor messagePostProcessor,
        // CorrelationData correlationData
        rabbitTemplate.convertAndSend("springboot.queue", "Hello RabbitMQ!");
    }
}
