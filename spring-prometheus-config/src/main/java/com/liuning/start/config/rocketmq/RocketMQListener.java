package com.liuning.start.config.rocketmq;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RocketMQListener implements MessageListenerConcurrently {

    private static final Logger log = LoggerFactory.getLogger(RocketMQListener.class);

    /**
     * 消费消息
     *
     * @param msgs    消息列表
     * @param context 上下文
     * @return 消费状态
     */
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        for (MessageExt msg : msgs) {
            try {
                String topic = msg.getTopic();
                String tag = msg.getTags();
                String msgId = msg.getMsgId();
            } catch (Exception e) {
                log.error("RocketMQConsumer system exception", e);
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
