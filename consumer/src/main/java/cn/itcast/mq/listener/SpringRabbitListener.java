package cn.itcast.mq.listener;

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

    @RabbitListener(queues = "queue.fanout1")
    public void listenFanoutQueue1Message(String message) {
        System.out.println("简单队列，监听到queue.fanout1的消息是：【" + message + "】");
    }

    @RabbitListener(queues = "queue.fanout2")
    public void listenFanoutQueue2Message(String message) {
        System.out.println("简单队列，监听到queue.fanout2的消息是：【" + message + "】");
    }
}
