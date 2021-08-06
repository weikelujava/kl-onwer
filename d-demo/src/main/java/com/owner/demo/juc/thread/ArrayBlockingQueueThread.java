package com.owner.demo.juc.thread;

import com.alibaba.fastjson.JSON;
import com.owner.demo.juc.init.ArrayBlockingQueueInit;
import com.owner.demo.model.CommonQueue;
import com.owner.demo.utils.ThreadPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author lukewei
 * @date 2021/8/6 14:44
 */
@Slf4j
@Component
public class ArrayBlockingQueueThread {

    @PostConstruct
    private void init(){
        ThreadPoolUtil.THREAD_POOL.execute(this::run);
    }

    /**
     * 添加禁止显示警告
     */
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        ArrayBlockingQueueInit instance = ArrayBlockingQueueInit.getInstance();

        if (null != instance){
            while (true){
                CommonQueue take = instance.take();
                log.info("ArrayBlockingQueue 消费消息：{}", JSON.toJSONString(take));
            }
        }
    }
}
