package com.owner.demo.juc.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

/**
 * @author lukewei
 * @date 2021/7/29 15:33
 *
 * ArrayBlockingQueue 是一个有界的阻塞队列
 * FIFO
 */
public class ArrayBlockingQueueDemo {




    public static void main(String[] args) {
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue<String>(3);
        Producer producer = new Producer(arrayBlockingQueue);
        Consumer consumer = new Consumer(arrayBlockingQueue);

        new Thread(producer).start();
    }

    static class Producer implements Runnable{

        protected ArrayBlockingQueue queue = null;

        public Producer(ArrayBlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {

                for (int i = 1; i < 4; i++) {
                    queue.put( i+"");
                    Thread.sleep(1000);
                }
                System.out.println("添加元素到队列，队列大小："+queue.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class Consumer implements Runnable{

        protected ArrayBlockingQueue queue = null;

        public Consumer(ArrayBlockingQueue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                System.out.println(queue.take());
                System.out.println("取出一个数据后，队列大小"+queue.size());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
