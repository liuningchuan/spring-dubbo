package com.liuning.start.config.rocketmq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * RocketMQ生产者配置
 *
 * @author liuning
 * @since 2020-11-29 23:36
 */
//@Configuration
public class RocketMQProducerConfig {

    @Value("${rocketmq.address}")
    private String namesrvAddress;

    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    @Bean(name = "producer", initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer();
        defaultMQProducer.setNamesrvAddr(namesrvAddress);
        defaultMQProducer.setProducerGroup(producerGroup);

        return defaultMQProducer;
    }
}
