package com.maguasoft.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public abstract class AbstractProducer {
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
            sendMessage(channel);
        } catch (Exception e) {
            log.error("Cause by: ", e);
        }
    }

    protected abstract void sendMessage(Channel channel) throws IOException;
}
