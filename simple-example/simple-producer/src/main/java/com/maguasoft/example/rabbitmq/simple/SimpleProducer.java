package com.maguasoft.example.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class SimpleProducer {

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
        try (Connection connection = connectionFactory.newConnection(); Channel channel = connection.createChannel()) {
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
            channel.queueDeclare("simple.queue", true, false, false, null);

            /*
              发送消息
              exchange: 交换机名称，simple 模式下没有交换机，所以值为 ""，也就是默认值
              routingKey: 路由名称，如果使用默认的exchange时，routingKey必须queue值一样
              props: 参数
              body: 消息的内容
             */
            channel.basicPublish("", "simple.queue", null, "Hello Simple mode of RabbitMQ".getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("Cause by: ", e);
        }
    }
}
