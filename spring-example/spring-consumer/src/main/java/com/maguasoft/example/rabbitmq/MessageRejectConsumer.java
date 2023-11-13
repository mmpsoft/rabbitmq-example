package com.maguasoft.example.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class MessageRejectConsumer {
    @RabbitListener(queues = "spring.general.queue", ackMode = "MANUAL")
    public void messageReject(Message message, Channel channel,
                              @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        // 拒绝消息，并且不重新投递回原队列中
        log.info("MessageRejectConsumer: {}", deliveryTag);
        channel.basicNack(deliveryTag, false, false);
    }
}
