package com.maguasoft.example.rabbitmq.ack;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class RabbitMQAckConsumer {

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
        channel.basicConsume("ack.queue", true, new DefaultConsumer(channel) {
            /**
             * @param consumerTag 消息的标识
             * @param envelope 消息的元数据，可以获取到交换机、路由key等信息
             * @param properties 参数
             * @param body 消息内容
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    log.info("consumerTag: {}", consumerTag);
                    log.info("envelope.Exchange: {}", envelope.getExchange());
                    log.info("envelope.RoutingKey: {}", envelope.getRoutingKey());
                    log.info("properties: {}", properties);
                    log.info("body: {}", new String(body));

                    // 确认消费成功
                    channel.basicAck(envelope.getDeliveryTag(), true);
                } catch (Exception e) {
                    // multiple: true表示拒绝所有DeliveryTag的消息, false表示只拒绝当前这条DeliveryTag的消息
                    // requeue: true表示重新投递回原队列，false表示丢弃
                    channel.basicNack(envelope.getDeliveryTag(), true, true);
                }
            }
        });
    }
}
