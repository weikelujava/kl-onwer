package com.owner.demo.controller;

import com.owner.demo.utils.ThreadPoolUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * @author lukewei
 * @date 2021/7/29 9:40
 */
@RestController
public class CountDownLatchController {


    public static void main(String[] args) throws InterruptedException {

        long begin = System.currentTimeMillis();
        System.out.println("开始时间："+begin);
        final CountDownLatch latch = new CountDownLatch(2);

        ThreadPoolUtil.THREAD_POOL.execute(()->{
            try {
                Thread.sleep(3000);
                System.out.println("第一个线程，执行了3S");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });


        ThreadPoolUtil.THREAD_POOL.execute(()->{
            try {
                Thread.sleep(7000);
                System.out.println("第二个线程，执行了7S");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });

        latch.await();
        System.out.println("计数器："+latch.getCount());
        long end = System.currentTimeMillis();
        System.out.println("结束时间："+end);
        System.out.println("代码执行 "+(end-begin)+ " ms");
    }


    @GetMapping("/countDownLatch")
    public void countDownLatchTest(){
        long begin = System.currentTimeMillis();
        System.out.println("开始时间："+begin);
        final CountDownLatch latch = new CountDownLatch(2);

        ThreadPoolUtil.THREAD_POOL.execute(()->{
            try {
                Thread.sleep(3000);
                System.out.println("第一个线程，执行了3S");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });


        ThreadPoolUtil.THREAD_POOL.execute(()->{
            try {
                Thread.sleep(7000);
                System.out.println("第二个线程，执行了7S");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("计数器："+latch.getCount());
        long end = System.currentTimeMillis();
        System.out.println("结束时间："+end);
        System.out.println("代码执行 "+(end-begin)+ " ms");
    }
}
