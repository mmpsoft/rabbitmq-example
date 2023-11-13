package com.maguasoft.example.rabbitmq;

import com.maguasoft.example.rabbitmq.ack.SpringAckProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class SpringAckProducerTest {

    @Autowired
    private SpringAckProducer producer;

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
        producer.sendMessage("spring.ack.exchange", "asdfasdf");

        // 等待回调处理完成
        Thread.sleep(1000);
    }

    @Test
    public void testAckSuccessed() throws InterruptedException {
        // 为测试 ReturnCallback，可以设置一个不存在的 routingKey 触发 ReturnCallback 回调
        producer.sendMessage("spring.ack.exchange", "info");

        // 等待回调处理完成
        Thread.sleep(1000);
    }
}
