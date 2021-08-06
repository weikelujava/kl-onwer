package com.owner.demo.juc.init;

import com.alibaba.fastjson.JSON;
import com.owner.demo.model.CommonQueue;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author lukewei
 * @date 2021/8/6 14:15
 */
@Slf4j
public class ArrayBlockingQueueInit {


    public static ArrayBlockingQueueInit getInstance(){
        return Singleton.INSTANCE;
    }

    private final ArrayBlockingQueue<CommonQueue> queues = new ArrayBlockingQueue<>(20);

    /**
     * 阻塞队列put方法
     * @param queue 队列消息
     */
    public void put(CommonQueue queue){
        try {
            queues.put(queue);
        } catch (InterruptedException e) {
           log.info("阻塞队列存放消息失败，失败信息:{}", JSON.toJSONString(queue));
        }
    }

    /**
     * 阻塞队列take方法
     * @return 队列消息
     */
    public CommonQueue take(){
        try {
            return queues.take();
        } catch (InterruptedException e) {
            return null;
        }
    }


    /**
     * 静态内部类 创建单例模式
     *
     * 由JVM保证线程安全
     */
    public static class Singleton{

        private static final ArrayBlockingQueueInit INSTANCE = new ArrayBlockingQueueInit();

    }

}
