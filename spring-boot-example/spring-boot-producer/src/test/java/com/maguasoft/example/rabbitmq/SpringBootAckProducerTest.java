package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.producer.SpringBootAckProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SpringBootAckProducerTest {
    @Autowired
    private SpringBootAckProducer producer;

    @Test
    public void testAckConfirm() throws InterruptedException {
        // 为测试 ConfirmCallback，可以设置一个不存在的 Exchange 触发 ConfirmCallback 回调
        producer.sendMessage("akjsldkfjalsjlkj", "asdfasdf");

        // 等待回调处理完成
        Thread.sleep(1000);
    }

    @Test
    public void testAckReturn() throws InterruptedException {
        // 为测试 ReturnCallback，可以设置一个不存在的 routingKey 触发 ReturnCallback 回调
        producer.sendMessage("springboot.ack.exchange", "asdfasdf");

        // 等待回调处理完成
        Thread.sleep(1000);
    }
}
