package com.owner.demo.juc.controller;

import com.owner.demo.utils.ThreadPoolUtil;

/**
 * volatile
 * 特性：可见性、顺序性、单次读或者写操作的原子性
 * 简单的i++操作，既有写也有读操作，所以不能保证原子性
 *
 * @author lukewei
 * @date 2021/8/6 16:43
 */
public class VolatileControllerTest {

    volatile int i;

    public void addI(){
        i++;
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileControllerTest test = new VolatileControllerTest();
        for (int i = 0; i < 1000; i++) {
            ThreadPoolUtil.THREAD_POOL.execute(()->{
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.addI();
            });
        }

        Thread.sleep(10000);
        System.out.println(test.i);
    }
}
