package com.alibaba.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.cai
 * @date 2020/3/25 - 11:45
 * 同步队列，产生一个消费一个
 */
public class SynchrousQueueDemo {
    public static void main(String[] args) {
        //默认为非公平锁
        BlockingQueue<String> blockingQueue = new SynchronousQueue<String>();

        new Thread(()->{
            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"AA").start();


        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t get 1");
                blockingQueue.take();
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t get 1");
                blockingQueue.take();
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName()+"\t get 1");
                blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"BB").start();
    }
}
