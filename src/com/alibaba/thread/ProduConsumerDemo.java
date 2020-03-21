package com.alibaba.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.cai
 * @date 2020/3/18 - 13:58
 * 题目：现在有两个线程，可以操作初始值为0的一个变量
 * 实现一个线程对该变量加1，一个线程对改变了减1，
 * 实现交替，来10轮，变量初始值为零
 *
 * 1.高举低耦合前提下，线程、操作、资源类
 * 2.判断/干活/通知
 * 3.防止多线程的虚假唤醒   if(number !=0){this.wait();}应该使用while
 */

class AirCondition{
    private int number = 0;

    /*public synchronized  void increment() throws Exception{
        //1.判断
        //多线程的交互机制，判断条件必须使用while
        // if(number !=0){
        //
        //            this.wait();
        //        }
        while(number !=0){

            this.wait();
        }
        //2.干活
        number ++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);

        //3.通知
        this.notifyAll();
    }

    public synchronized  void decrement() throws Exception{
        while(number ==0) this.wait();
        number --;
        System.out.println(Thread.currentThread().getName()+"\t"+number);

        this.notifyAll();
    }*/
    //可重入非公平递归锁
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void increment(){
        lock.lock();
        try {
            while(number!=0){
                condition.await();
            }
            number ++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try {
            while(number ==0 ){
                condition.await();
            }
            number --;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class ProduConsumerDemo {
    public static void main(String[] args) {
        AirCondition airCondition = new AirCondition();
        new Thread(()->{
            for (int i=0;i<=9;++i){
                try {
                    airCondition.increment();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(()->{
            for (int i=0;i<=9;++i) {
                try {
                    airCondition.decrement();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}
