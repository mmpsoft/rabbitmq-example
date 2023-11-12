package com.maguasoft.example.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringBootDirectProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        // String exchange      交换机
        // String routingKey    路由的Key，简单模式下，routingKey 就是queue name
        // Object message,      消息内容
        // MessagePostProcessor messagePostProcessor,
        // CorrelationData correlationData
        rabbitTemplate.convertAndSend("springboot.direct.exchange", "info", "Hello SpringBoot Direct RabbitMQ! | info");
        rabbitTemplate.convertAndSend("springboot.direct.exchange", "error", "Hello SpringBoot Direct RabbitMQ! | error");
        rabbitTemplate.convertAndSend("springboot.direct.exchange", "warn", "Hello SpringBoot Direct RabbitMQ! | warn");
    }
}
