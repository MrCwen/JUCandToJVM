package com.alibaba.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.cai
 * @date 2020/3/25 - 11:53
 */
public class ConsumerAndProducer {
    private static Lock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    private static  int num = 0;
    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            //生产者
            new Thread(()->{
                lock.lock();
               try {
                   while(num !=0) condition.await();
                   ++num;
                   System.out.println(Thread.currentThread().getName()+"生产了:"+num);
                   condition.signalAll();
               }catch (Exception e){
                   e.printStackTrace();
               }finally {
                   lock.unlock();
               }
            },String.valueOf(i)).start();

            //消费者
            new Thread(()->{
                lock.lock();
                try {
                    while(num ==0) condition.await();
                    --num;
                    System.out.println(Thread.currentThread().getName()+"消费了:"+num);
                    condition.signalAll();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            },String.valueOf(i)).start();
        }
    }
}
