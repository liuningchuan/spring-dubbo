package com.liuning.web.config.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * RocketMQ消费者配置
 *
 * @author liuning
 * @since 2020-12-03 23:54
 */
public class RocketMQConsumerConfig {

    /**
     * 集群地址
     */
    @Value("${rocketmq.address}")
    private String namesrvAddress;

    /**
     * 消费者群组
     */
    @Value("${rocketmq.producer.group}")
    private String consumerGroup;

    /**
     * topic
     */
    @Value("${rocketmq.topic}")
    private String topic;

    /**
     * tag 格式：A||B||C
     */
    @Value("${rocketmq.tag.group}")
    private String tag;

    @Resource
    private RocketMQListener listener;

    @Bean(name = "consumer", initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQPushConsumer consumer() {
        System.setProperty("rocketmq.client.log.loadconfig", "false");
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer();
        defaultMQPushConsumer.setNamesrvAddr(namesrvAddress);
        defaultMQPushConsumer.setConsumerGroup(consumerGroup);
        defaultMQPushConsumer.setInstanceName(String.valueOf(System.currentTimeMillis()));
        try {
            defaultMQPushConsumer.subscribe(topic, tag);
        } catch (MQClientException e) {
            throw new RuntimeException();
        }
        defaultMQPushConsumer.registerMessageListener(listener);
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);
        defaultMQPushConsumer.setConsumeMessageBatchMaxSize(32);
        defaultMQPushConsumer.setVipChannelEnabled(false);
        return defaultMQPushConsumer;
    }
}
