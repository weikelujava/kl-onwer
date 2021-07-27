package com.owner.rocketmq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lukewei
 * @date 2021/7/26 10:00
 */
@RestController
public class Producer1Controller {



    @GetMapping("/rocketmq/produce")
    public void orderProducer() throws Exception {
        MQProducer producer = new DefaultMQProducer("example_group_name");
        producer.start();


        String[] tags = new String[]{};

        for (int i = 0; i < 20; i++) {
            int orderId = i % 10;

//            Message message = new Message()
        }


    }
}
