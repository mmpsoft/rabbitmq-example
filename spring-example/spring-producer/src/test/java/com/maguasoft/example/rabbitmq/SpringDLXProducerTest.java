package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.dlx.SpringDLXProducer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class SpringDLXProducerTest {

    @Autowired
    private SpringDLXProducer producer;

    /**
     * 测试达到 Queue 的最大长度时，消息是否转发到死信队列
     */
    @Test
    public void testQueueMaxLength() throws InterruptedException {
        // spring.general.queue 设置了 max-length 为 5 条，发送 5 条以上的消息，会触发死信队列
        for (int i = 0; i < 1; i++) {
            producer.sendMessage();
        }
    }

    /**
     * 测试消息被消费者拒绝后，消息是否转发到死信队列
     */
    @Test
    public void testReject() {
        // 查看 MessageRejectConsumer
    }

    /**
     * 测试消息被过期了，消息是否转发到死信队列
     */
    @Test
    public void testMessageTTL() throws InterruptedException {
        // spring.general.queue 设置了 TTL 时间为 15s，在 Web 控制台观察 Queue 的变化即可
    }
}
