package com.maguasoft.example.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpringBootFanoutProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage() {
        // String exchange      交换机
        // String routingKey    路由的Key，简单模式下，routingKey 就是queue name
        // Object message,      消息内容
        // MessagePostProcessor messagePostProcessor,
        // CorrelationData correlationData
        rabbitTemplate.convertAndSend("springboot.fanout.exchange", "","Hello SpringBoot Fanout RabbitMQ!");
    }
}
