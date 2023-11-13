package com.maguasoft.example.rabbitmq.ack;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class RabbitMQAckProducer {

    public void sendMessage() {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("172.27.153.46");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/"); // 虚拟主机，默认 /
        connectionFactory.setUsername("test"); // 用户名
        connectionFactory.setPassword("test"); // 密码

        // 创建 Connection
        // 创建 Channel
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {

            // 在此通道上启用确认模式
            channel.confirmSelect();

            /*
              创建 Queue
              queue: 队列名称
              durable: 是否持久化，当 MQ 重启之后，数据不会丢失。true: 是，false: 否
              exclusive:
                   是否独占，表示只能一个消费者来消费这个队列
                   当 Connection 关闭时，是否删除队列
              autoDelete: 是否自动删除，当没有 Consumer 时，自动删除掉
              arguments: 参数
             */
            // 如果 simple.queue 队列不存在，则会创建该队列，如有已存在不会创建该队列
            channel.queueDeclare("ack.queue", true, false, false, null);

            // 添加消息的Confirm监听
            channel.addConfirmListener(new ConfirmListener() {
                @Override
                public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                    log.info("deliveryTag: {} acked", deliveryTag);
                }

                @Override
                public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                    log.info("deliveryTag: {} no acked", deliveryTag);
                }
            });

            // 添加消息的 Return 监听
            channel.addReturnListener(new ReturnListener() {
                @Override
                public void handleReturn(int replyCode, String replyText, String exchange,
                                         String routingKey, AMQP.BasicProperties properties, byte[] body) {
                    log.info("replyCode: {}, " +
                            "replyText: {}, " +
                            "exchange: {}, " +
                            "routingKey: {}, " +
                            "properties: {}, " +
                            "body: {}",
                            replyCode,
                            replyText,
                            exchange,
                            routingKey,
                            properties,
                            new String(body));
                }
            });

            /*
              发送消息
              exchange: 交换机名称，simple 模式下没有交换机，所以值为 ""，也就是默认值
              routingKey: 路由名称，如果使用默认的exchange时，routingKey必须queue值一样
              props: 参数
              body: 消息的内容
             */
            // 因为有多个消费者，为了测试方便，for 循环生产消息
            for (int index = 1; index <= 100; index++) {
                channel.basicPublish("", "workqueues.queue", null,
                        ("Hello Simple mode of RabbitMQ-" + index).getBytes(StandardCharsets.UTF_8));
            }

        } catch (Exception e) {
            log.error("Cause by: ", e);
        }
    }
}
