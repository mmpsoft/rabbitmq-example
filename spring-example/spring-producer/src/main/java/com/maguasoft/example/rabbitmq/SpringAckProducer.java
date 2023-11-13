package com.maguasoft.example.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringAckProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String exchange, String routingKey) {
        // confirm 模式，如果消息投递到 Exchange 成功或失败时，触发此回调
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            // ack: true表示消息成功到达 Exchange
            // cause: 消息投递失败的原因
            log.info("ConfirmCallback: ack: {}, cause: {}", ack, cause);
        });

        // 发送消息时设置强制标志, 仅当提供了 returnCallback 时才适用。
        rabbitTemplate.setMandatory(true);
        // return 回退模式，如果 Exchange 将消息路由到 Queue 失败时，触发此回调
        // 1、如果 Exchange 无法将消息路由到 Queue，则丢弃消息（默认）
        // 2、如果 Exchange 无法将消息路由到 Queue，同时设置了 ReturnCallback，则回触发回调函数
        rabbitTemplate.setReturnsCallback(returned -> log.info("ReturnsCallback: {}", returned));

        // 发送消息
        rabbitTemplate.convertAndSend(exchange, routingKey, "Hello Spring Ack RabbitMQ-info");

        // 使用同步 Ack 处理
        // Boolean isAcked = rabbitTemplate.invoke(op -> {
        //     // 发送消息
        //     op.send("", "", null);
        //     // 阻塞等待 Broker 的 Ack 响应，超时会报异常。
        //     return op.waitForConfirms(1000);
        // });
        // if (Boolean.TRUE.equals(isAcked)) {
        //     // ....
        // }
    }
}
