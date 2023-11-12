package com.maguasoft.example.rabbitmq.simple;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SimpleConsumer {

    public void onMessage() throws Exception {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("172.27.153.46");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/"); // 虚拟主机，默认 /
        connectionFactory.setUsername("test"); // 用户名
        connectionFactory.setPassword("test"); // 密码

        // 创建 Connection
        // 创建 Channel
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
            /*
              接收消息
              queue: 队列名称
              autoAck: 是否自动确认
              callback: 回调处理的函数，有 DefaultConsumer 实现类
             */
        channel.basicConsume("simple.queue", true, new DefaultConsumer(channel) {
            /**
             * @param consumerTag 消息的标识
             * @param envelope 消息的元数据，可以获取到交换机、路由key等信息
             * @param properties 参数
             * @param body 消息内容
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {
                log.info("consumerTag: {}", consumerTag);
                log.info("envelope.Exchange: {}", envelope.getExchange());
                log.info("envelope.RoutingKey: {}", envelope.getRoutingKey());
                log.info("properties: {}", properties);
                log.info("body: {}", new String(body));
            }
        });
    }
}
