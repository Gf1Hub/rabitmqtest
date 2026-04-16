package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest

public class SpringAmqpTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息到simple queue
     */
    @Test
    public void testSendMessage() {
        // 1.创建消息
        String queueName = "simple.queue";
        String message = "Hello, Spring Boot 2.0!";
        // 2.发送消息
        rabbitTemplate.convertAndSend(queueName, message);
    }


    /**
     * 发送消息到work queue
     */
    @Test
    public void testSendMessageWorkQueue() throws InterruptedException {
        // 1.创建消息
        String queueName = "simple.queue";
        String message = "Hello, testSendMessageWorkQueue!";
        // 2.发送消息
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message);
            Thread.sleep(20);
        }
    }

    /**
     * 发送消息到Fanout Exchange
     */
    @Test
    public void testSendMessageFanoutExChange() {
        // 交换机 名
        String exchangeName = "exchange.fanout";
        String message = "Hello, testSendMessageFanoutExChange!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }


    @Test
    public void testSendMessageDirectExChange() {
        // 交换机 名
        String exchangeName = "exchange.direct";
        String message = "Hello,yellow!";
        rabbitTemplate.convertAndSend(exchangeName, "yellow", message);
    }
}
