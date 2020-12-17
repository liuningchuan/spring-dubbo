package com.liuning.web.config.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.context.annotation.Bean;

/**
 * RocketMQ消费者配置
 *
 * @author liuning
 * @since 2020-12-03 23:54
 */
public class RocketMQConsumerConfig {

    @Bean(name = "consumer", initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQPushConsumer consumer() {
        return new DefaultMQPushConsumer();
    }
}
