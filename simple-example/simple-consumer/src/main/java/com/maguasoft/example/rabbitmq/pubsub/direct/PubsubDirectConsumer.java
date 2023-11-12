package com.maguasoft.example.rabbitmq.pubsub.direct;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PubsubDirectConsumer {

    private final String consumerName;
    private final String queueName;

    public PubsubDirectConsumer(String consumerName, String queueName) {
        this.consumerName = consumerName;
        this.queueName = queueName;
    }

    public void onMessage() throws Exception {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("172.27.153.46");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/"); // 虚拟主机，默认 /
        connectionFactory.setUsername("test"); // 用户名
        connectionFactory.setPassword("test"); // 密码

        // 创建 Connection
        Connection connection = connectionFactory.newConnection();
        // 创建 Channel
        Channel channel = connection.createChannel();
            /*
              接收消息
              queue: 队列名称
              autoAck: 是否自动确认
              callback: 回调处理的函数，有 DefaultConsumer 实现类
             */
        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            /**
             *
             * @param consumerTag 消息的标识
             * @param envelope 消息的元数据，可以获取到交换机、路由key等信息
             * @param properties 参数
             * @param body 消息内容
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties, byte[] body) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                log.info("QueueName: {}, {} 接收到: {}", queueName, consumerName, new String(body));
            }
        });
    }
}
