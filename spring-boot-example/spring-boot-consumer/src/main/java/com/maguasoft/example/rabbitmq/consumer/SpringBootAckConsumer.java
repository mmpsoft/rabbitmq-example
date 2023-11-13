package com.maguasoft.example.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class SpringBootAckConsumer {

    /**
     * ackMode: ack类型，参考 org.springframework.amqp.core.AcknowledgeMode，
     * 查看 https://www.yuque.com/jeremyspace/ln8k0o/hgts8ggiazknm7ta#Jpl3a
     *
     * @param message
     * @param channel
     * @param deliveryTag
     * @throws IOException
     */
//    @RabbitHandler
    @RabbitListener(queues = "springboot.ack.queue", ackMode = "MANUAL")
    public void onAckMessage(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        // Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        // Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);

        try {
            log.info("SpringBootAckConsumer-消费者1 message: {}", message.getPayload());
            log.info("SpringBootAckConsumer-消费者1 channel: {}", channel);
            log.info("SpringBootAckConsumer-消费者1 deliveryTag: {}", deliveryTag);

            // 为测试 basicReject 场景，使用 1/0 异常触发
            // int i = 1 / 0;

            channel.basicAck(deliveryTag, true);
        } catch (Exception e) {
            log.error("SpringBootAckConsumer-消费者1 消费异常了: ", e);
            channel.basicReject(deliveryTag, true);
        }
    }
}
