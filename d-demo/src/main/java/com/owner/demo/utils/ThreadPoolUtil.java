package com.owner.demo.utils;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author lukewei
 * @date 2021/7/29 9:45
 */
public class ThreadPoolUtil {

    private ThreadPoolUtil(){}


    private static Integer cpuCoreSize(){
        return Runtime.getRuntime().availableProcessors() != 0 ? Runtime.getRuntime().availableProcessors() : 8;
    }



    public static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(
            cpuCoreSize(),
            cpuCoreSize()*2,
            10,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(100),
            new ThreadFactoryBuilder().setNamePrefix("my-thread-pool").build(),
            new ThreadPoolExecutor.CallerRunsPolicy());

}
