package com.owner.demo.juc.controller;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.owner.demo.juc.init.ArrayBlockingQueueInit;
import com.owner.demo.juc.init.DelayQueueInit;
import com.owner.demo.juc.init.DelayQueueItem;
import com.owner.demo.model.CommonQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author lukewei
 * @date 2021/8/6 14:37
 */
@Slf4j
@RestController
public class BlockingQueueController {


    /**
     * 阻塞队列
     */
    @GetMapping("/arrayBlockingQueue/test")
    public void arrayBlockingQueueTest(){

        ArrayBlockingQueueInit instance = ArrayBlockingQueueInit.getInstance();
        CommonQueue queue = new CommonQueue();
        String str = RandomUtil.randomUUID();
        queue.setKey(str);
        queue.setData(str);
        log.info("ArrayBlockingQueue 生产消息：{}", JSON.toJSONString(queue));
        instance.put(queue);
    }


    /**
     * 延迟队列
     */
    @GetMapping("/delayedQueue/test")
    public void delayedQueueTest(){
        DelayQueueItem d1 = new DelayQueueItem("第一个执行",5L, TimeUnit.SECONDS);
        DelayQueueItem d2 = new DelayQueueItem("第二个执行",10L, TimeUnit.SECONDS);
        DelayQueueItem d3 = new DelayQueueItem("第三个执行",15L, TimeUnit.SECONDS);

        DelayQueue<DelayQueueItem> queues = DelayQueueInit.getDelayedQueue();
        queues.put(d2);
        queues.put(d3);
        queues.put(d1);
        log.info("DelayedQueue 生产消息：{}", JSON.toJSONString(queues));
    }
}
