package com.liuning.web.config.rocketmq;

import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.common.protocol.heartbeat.SubscriptionData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * RocketMQ消费者配置
 *
 * @author liuning
 * @since 2020-12-03 23:54
 */
@Configuration
public class RocketMQConsumerConfig {

    /**
     * 集群A地址
     */
    @Value("${rocketmq.address}")
    private String namesrvAddressA;

    /**
     * 集群B地址
     */
    @Value("${rocketmq.address}")
    private String namesrvAddressB;

    /**
     * 消费者群组
     */
    @Value("${rocketmq.producer.group}")
    private String consumerGroup;

    /**
     * access key
     */
    @Value("${rocketmq.consumer.access.key}")
    private String consumerAccessKey;

    /**
     * secret key
     */
    @Value("${rocketmq.consumer.secret.key}")
    private String consumerSecretKey;

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
        defaultMQPushConsumer.setNamesrvAddr(namesrvAddressA);
        defaultMQPushConsumer.setConsumerGroup(consumerGroup);
        defaultMQPushConsumer.setInstanceName(String.valueOf(System.currentTimeMillis()));
        defaultMQPushConsumer.setSubscription(this.getSubscription());
        defaultMQPushConsumer.registerMessageListener(listener);
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        defaultMQPushConsumer.setMessageModel(MessageModel.CLUSTERING);
        defaultMQPushConsumer.setConsumeMessageBatchMaxSize(32);
        defaultMQPushConsumer.setVipChannelEnabled(false);
        return defaultMQPushConsumer;
    }

    private Map<String, String> getSubscription() {
        Map<String, String> subscription = new HashMap<>();
        subscription.put(topic, tag);
        return subscription;
    }

    @Bean(name = "defaultMQPushConsumer", initMethod = "start", destroyMethod = "shutdown")
    public DefaultMQPushConsumer defaultMQPushConsumer() {
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer(
                new AclClientRPCHook(new SessionCredentials(consumerAccessKey, consumerSecretKey)));
        defaultMQPushConsumer.setNamesrvAddr(namesrvAddressB);
        defaultMQPushConsumer.setConsumerGroup(consumerGroup);
        defaultMQPushConsumer.setInstanceName(String.valueOf(System.currentTimeMillis()));
        try {
            defaultMQPushConsumer.subscribe(topic, SubscriptionData.SUB_ALL);
        } catch (MQClientException e) {
            throw new RuntimeException();
        }
        defaultMQPushConsumer.registerMessageListener(listener);
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        defaultMQPushConsumer.setMessageModel(MessageModel.BROADCASTING);
        defaultMQPushConsumer.setConsumeMessageBatchMaxSize(32);
        defaultMQPushConsumer.setVipChannelEnabled(false);
        defaultMQPushConsumer.setPullInterval(0);
        return defaultMQPushConsumer;
    }
}
