package com.owner.rocketmq.producer;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.owner.rocketmq.mode.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author lukewei
 * @date 2021/7/21 15:19
 *
 */
@RestController
@Slf4j
public class ProducerController {


    @Autowired
    private RocketMQTemplate rocketMqTemplate;



    @GetMapping("/rocketmq/send")
    public void sendMessage(){
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        log.info("开始发送消息...");
        Order order  = new Order();
        order.setId(snowflake.nextId());
        order.setOrderNo(snowflake.nextIdStr());
        long foreignPrice = RandomUtil.randomLong(200);
        order.setForeignPrice(foreignPrice);
        order.setActualAmount(foreignPrice);

        Date date = DateUtil.date();
        order.setCreateTime(date.getTime());
        order.setCreateTimeStr(DateUtil.formatDateTime(date));
        int expireTime = 600;
        order.setExpireTime(expireTime);

        Message<Order> message = new GenericMessage<Order>(order);
        //
        rocketMqTemplate.convertAndSend("demo_order:stock",message);
        log.info("消息发送成功...,消息内容：{}",order.toString());
    }
}
