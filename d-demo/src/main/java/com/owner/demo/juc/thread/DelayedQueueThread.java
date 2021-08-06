package com.owner.demo.juc.thread;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.owner.demo.juc.init.ArrayBlockingQueueInit;
import com.owner.demo.juc.init.DelayQueueInit;
import com.owner.demo.juc.init.DelayQueueItem;
import com.owner.demo.model.CommonQueue;
import com.owner.demo.utils.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.DelayQueue;

/**
 * @author lukewei
 * @date 2021/8/6 14:44
 */
@Slf4j
@Component
public class DelayedQueueThread {

    @PostConstruct
    private void init(){
        ThreadPoolUtil.THREAD_POOL.execute(this::run);
    }

    /**
     * 添加禁止显示警告
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        DelayQueue<DelayQueueItem> delayedQueue = DelayQueueInit.getDelayedQueue();
        if (null != delayedQueue){
            while (true){
                try {
                    DelayQueueItem take = delayedQueue.take();
                    log.info("ArrayBlockingQueue 消费消息：{},当前时间：{}", JSON.toJSONString(take), DateUtil.now());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
