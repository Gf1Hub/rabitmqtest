package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

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

    /**
     * 测试发送消息到Direct Exchange
     */
    @Test
    public void testSendMessageDirectExChange() {
        // 交换机 名
        String exchangeName = "exchange.direct";
        String message = "Hello,yellow!";
        rabbitTemplate.convertAndSend(exchangeName, "yellow", message);
    }

    @Test
    public void testSendMessageTopicExChange() {
        // 交换机 名
        String exchangeName = "exchange.topic";
        String message = "Hello,China!";
        rabbitTemplate.convertAndSend(exchangeName, "china", message);
        String message1 = "Hello,news!";
        rabbitTemplate.convertAndSend(exchangeName, "china.news", message1);
    }

    @Test
    public void testObjectQueue() {
        HashMap<String, Object> msg = new HashMap<>();
        msg.put("name", "刘翔");
        msg.put("age", 18);
        rabbitTemplate.convertAndSend("object.queue", msg);
    }
}
