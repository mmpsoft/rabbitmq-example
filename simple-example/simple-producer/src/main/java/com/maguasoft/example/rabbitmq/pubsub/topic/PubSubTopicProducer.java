package com.maguasoft.example.rabbitmq.pubsub.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

@Slf4j
public class PubSubTopicProducer {

    public static final String EXCHANGE_FANOUT_NAME = "pubsub.topic";
    public static final String EXCHANGE_FANOUT_QUEUE1_NAME = "pubsub.topic.queue1";
    public static final String EXCHANGE_FANOUT_QUEUE2_NAME = "pubsub.topic.queue2";

    public static final String ROUTING_KEY_ORANGE = "*.orange.*";
    public static final String ROUTING_KEY_RABBIT = "*.*.rabbit";
    public static final String ROUTING_KEY_LAZY = "Lazy.#";

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
            /*
               创建 Exchange
               exchange: 交换机名称，自定义名称或指定已创建的交换机
               type: 交换机类型, 可使用 BuiltinExchangeType 枚举类
                    DIRECT: 定向
                    FANOUT: 广播
                    TOPIC: 通配符
               durable: 是否持久化
               autoDelete: 是否自动删除
               internal: 是否RabbitMQ内部使用。一般为false
               arguments: 参数
             */
            channel.exchangeDeclare(EXCHANGE_FANOUT_NAME, BuiltinExchangeType.TOPIC, true, false, false, null);

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
            // 如果 pubsub.fanout.queue1 队列不存在，则会创建该队列，如有已存在不会创建该队列
            // 如果 pubsub.fanout.queue2 队列不存在，则会创建该队列，如有已存在不会创建该队列
            channel.queueDeclare(EXCHANGE_FANOUT_QUEUE1_NAME, true, false, false, null);
            channel.queueDeclare(EXCHANGE_FANOUT_QUEUE2_NAME, true, false, false, null);

            /*
              Exchange 与 Queue 绑定
              queue: 队列名称
              exchange: 交换机名称
              routingKey: 路由Key，绑定规则，决定 Exchange 将消息转发到那个 Queue。
              如果交换机的类型为 fanout 类型，routingKey设置为 ""
             */
            // 将 pubsub.direct.queue1 绑定到 pubsub.direct 交换机，并指定 routingKey 通配符为 *.orange.*
            channel.queueBind(EXCHANGE_FANOUT_QUEUE1_NAME, EXCHANGE_FANOUT_NAME, ROUTING_KEY_ORANGE);
            // 将 pubsub.direct.queue2 绑定到 pubsub.direct 交换机，并指定 routingKey 通配符为 "*.*.rabbit"
            channel.queueBind(EXCHANGE_FANOUT_QUEUE2_NAME, EXCHANGE_FANOUT_NAME, ROUTING_KEY_RABBIT);
            // 将 pubsub.direct.queue2 绑定到 pubsub.direct 交换机，并指定 routingKey 通配符为 Lazy.#
            channel.queueBind(EXCHANGE_FANOUT_QUEUE2_NAME, EXCHANGE_FANOUT_NAME, ROUTING_KEY_LAZY);

            /*
              发送消息
              exchange: 交换机名称，simple 模式下没有交换机，所以值为 ""，也就是默认值
              routingKey: 路由名称，如果使用默认的 exchange 时，routingKey 必须 queue 值一样
              props: 参数
              body: 消息的内容
             */
            // 通过 pubsub.topic 交换机转发，并指定 routingKey 通配符为 *.orange.*
            // 下面两条消息没有匹配的到任何的routingKey, 因此消息会丢失
            channel.basicPublish(EXCHANGE_FANOUT_NAME, "test.orange", null, "Hello Pubsub topic -- test.orange".getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(EXCHANGE_FANOUT_NAME, "orange.example2", null, "Hello Pubsub topic -- orange.example2".getBytes(StandardCharsets.UTF_8));
            // 只有这条消息的 routingKey 匹配 *.orange.* 成功, 才会转发到 queue1
            channel.basicPublish(EXCHANGE_FANOUT_NAME, "test.orange.example1", null, "Hello Pubsub topic -- test.orange.example1".getBytes(StandardCharsets.UTF_8));

            // 通过 pubsub.topic 交换机转发，并指定 routingKey 通配符为 "*.*.rabbit"
            // channel.basicPublish(EXCHANGE_FANOUT_NAME, "test.rabbit", null, "Hello Pubsub topic -- test.rabbit".getBytes(StandardCharsets.UTF_8));
            // channel.basicPublish(EXCHANGE_FANOUT_NAME, "rabbit.test", null, "Hello Pubsub topic -- test.rabbit".getBytes(StandardCharsets.UTF_8));
            // channel.basicPublish(EXCHANGE_FANOUT_NAME, "test.test.rabbit.test", null, "Hello Pubsub topic -- test.rabbit".getBytes(StandardCharsets.UTF_8));
            // channel.basicPublish(EXCHANGE_FANOUT_NAME, "test.test.test.rabbit", null, "Hello Pubsub topic -- test.rabbit".getBytes(StandardCharsets.UTF_8));

            // 只有这三条消息的 routingKey 匹配 *.*.rabbit 成功, 会转发到 queue2
            channel.basicPublish(EXCHANGE_FANOUT_NAME, "test.example1.rabbit", null, "Hello Pubsub topic -- test.example1.rabbit".getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(EXCHANGE_FANOUT_NAME, "test.example2.rabbit", null, "Hello Pubsub topic -- test.example2.rabbit".getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(EXCHANGE_FANOUT_NAME, "test.example3.rabbit", null, "Hello Pubsub topic -- test.example3.rabbit".getBytes(StandardCharsets.UTF_8));

            // 通过 pubsub.topic 交换机转发，并指定 routingKey 通配符为 Lazy.#
            // channel.basicPublish(EXCHANGE_FANOUT_NAME, "test.Lazy", null, "Hello Pubsub topic -- test.Lazy".getBytes(StandardCharsets.UTF_8));
            // 只有这三条消息的 routingKey 匹配 Lazy.# 成功, 会转发到 queue2
            channel.basicPublish(EXCHANGE_FANOUT_NAME, "Lazy", null, "Hello Pubsub topic -- Lazy".getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(EXCHANGE_FANOUT_NAME, "Lazy.test", null, "Hello Pubsub topic -- Lazy.test".getBytes(StandardCharsets.UTF_8));
            channel.basicPublish(EXCHANGE_FANOUT_NAME, "Lazy.test.example", null, "Hello Pubsub topic -- Lazy.test.example".getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            log.error("Cause by: ", e);
        }
    }
}
