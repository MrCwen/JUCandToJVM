package com.alibaba.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Mr.cai
 * @date 2020/3/25 - 14:53
 * 第四种获取java线程的方式，线程池
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        //固定线程数的线程池,注意线程的释放
        //ExecutorService threadPool = Executors.newFixedThreadPool(41);
        //final ExecutorService threadPool = Executors.newSingleThreadExecutor();
        ExecutorService threadPool = Executors.newCachedThreadPool();
        try{
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
        //一个线程的线程池

        //带缓存的可扩容的线程池
    }
}
