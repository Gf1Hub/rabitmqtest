package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueueMessage(String message) {
//        System.out.println("简单队列，监听到的消息是：【" + message + "】");
//    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueueMessage1(String message) throws InterruptedException {
        System.out.println("消费者1，监听到的消息是：【" + message + "】");
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueueMessage2(String message) throws InterruptedException {
        System.err.println("消费者2.....监听到的消息是：【" + message + "】");
        Thread.sleep(200);
    }

    /**
     * 监听 fanout 交换机
     * @param message
     */
    @RabbitListener(queues = "queue.fanout1")
    public void listenFanoutQueue1Message(String message) {
        System.out.println("消费者监听到queue.fanout1的消息是：【" + message + "】");
    }

    @RabbitListener(queues = "queue.fanout2")
    public void listenFanoutQueue2Message(String message) {
        System.out.println("消费者监听到queue.fanout2的消息是：【" + message + "】");
    }

    /**
     * 监听 direct 交换机
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "exchange.direct", type = ExchangeTypes.DIRECT),
            key = {"yellow", "blue"}
    ))
    public void listenDirectQueue1Message(String message) {
        System.out.println("消费者监听到direct.queue1的消息是：【" + message + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "exchange.direct", type = ExchangeTypes.DIRECT),
            key = {"red", "blue"}
    ))
    public void listenDirectQueue2Message(String message) {
        System.out.println("消费者监听到direct.queue2的消息是：【" + message + "】");
    }
}
