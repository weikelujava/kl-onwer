package com.owner.rocketmq.consumer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.owner.rocketmq.mode.Order;
import com.owner.rocketmq.vo.MessageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author lukewei
 * @date 2021/7/19 15:04
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "demo_order",consumerGroup = "demo_topic",selectorExpression = "stock")
public class RocketMqConsumer implements RocketMQListener<String> {


    public void onMessage(String message) {
        log.info("消费者2开始消费消息...");
        MessageVo messageVo = JSON.parseObject(message, MessageVo.class);
        Order order = JSON.parseObject(JSONObject.toJSONString(messageVo.getPayload()), Order.class);
        log.info("消费者2消费消息内容：{}",order);
    }
}
