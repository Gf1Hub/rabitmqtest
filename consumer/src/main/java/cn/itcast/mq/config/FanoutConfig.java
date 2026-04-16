package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    public static final String EXCHANGE_FANOUT = "exchange.fanout"; // 交换机名称
    public static final String QUEUE_FANOUT_1 = "queue.fanout1"; // 队列名称1
    public static final String QUEUE_FANOUT_2 = "queue.fanout2"; // 队列名称2

    /**
     * 创建交换机
     * @return
     */
    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(EXCHANGE_FANOUT);
    }

    /**
     * 创建声明队列1
     * @return
     */
    @Bean
    public Queue queue1() {
        return new Queue(QUEUE_FANOUT_1);
    }

    /**
     * 绑定队列1到交换机
     * @return
     */
    @Bean
    public Binding fanoutbinding1(Queue queue1, FanoutExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange);

    }

    /**
     * 创建声明队列2
     * @return
     */
    @Bean
    public Queue queue2() {
        return new Queue(QUEUE_FANOUT_2);
    }

    /**
     * 绑定队列2到交换机
     * @return
     */
    @Bean
    public Binding fanoutbinding2(Queue queue2, FanoutExchange exchange) {
        return BindingBuilder.bind(queue2).to(exchange);

    }

}
