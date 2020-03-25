package com.alibaba.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData1{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment () throws Exception{
        lock.lock();
        try {
            while(number !=0) condition.await();
            number ++;
            System.out.println(Thread.currentThread().getName()+"生产了"+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws Exception{
        lock.lock();
        try {
            while(number==0) condition.await();
            number--;
            System.out.println(Thread.currentThread().getName()+"消费了"+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
/**
 * @author Mr.cai
 * @date 2020/3/25 - 12:19
 * 题目L一个初始值为0的变量，两个变量交替操作，一个加1，一个减1，来无轮
 *
 * 1.线程  操作  资源类
 * 2.判断  干活   通知
 * 3.防止虚假唤醒
 *
 * 注意：不要直接操作变量，要操作对象里面的变量
 */
public class ConsumerAndProduceTDemo {
    public static void main(String[] args) {
        ShareData1 shareData1 = new ShareData1();
        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                try {
                    shareData1.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();

        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                try {
                    shareData1.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
    }
}
