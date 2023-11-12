package com.maguasoft.example.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringBootTopicProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        // String exchange      交换机
        // String routingKey    路由的Key，简单模式下，routingKey 就是queue name
        // Object message,      消息内容
        // MessagePostProcessor messagePostProcessor,
        // CorrelationData correlationData
        rabbitTemplate.convertAndSend("springboot.topic.exchange", "test.orange.example", "Hello SpringBoot Topic RabbitMQ! | test.orange.example");
        rabbitTemplate.convertAndSend("springboot.topic.exchange", "test.example.rabbit", "Hello SpringBoot Topic RabbitMQ! | test.example.rabbit");
        rabbitTemplate.convertAndSend("springboot.topic.exchange", "Lazy.test", "Hello SpringBoot Topic RabbitMQ! | Lazy.test");
        rabbitTemplate.convertAndSend("springboot.topic.exchange", "Lazy.test.example", "Hello SpringBoot Topic RabbitMQ! | Lazy.test.example");
    }
}
