package com.owner.demo.juc.init;


import lombok.Data;
import lombok.ToString;

import java.util.concurrent.DelayQueue;

/**
 * @author lukewei
 * @date 2021/8/6 15:03
 */
@Data
@ToString
public class DelayQueueInit{

    private static final DelayQueue<DelayQueueItem> QUEUES = new DelayQueue<>();

    /**
     * 静态内部类 创建单例模式
     *
     * 由JVM保证线程安全
     */
    public static class Singleton{

        private static final DelayQueueInit INSTANCE = new DelayQueueInit();

    }
    public static DelayQueueInit getInstance(){
        return Singleton.INSTANCE;
    }

    public static DelayQueue<DelayQueueItem> getDelayedQueue(){
        return QUEUES;
    }

}
