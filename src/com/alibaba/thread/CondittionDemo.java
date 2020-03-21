package com.alibaba.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.cai
 * @date 2020/3/18 - 17:21
 * 备注：多线程之间按顺序调用，实现A->B->C
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印五次，BB打印10次，CC打印15次
 * 来十轮
 */
class  ShareData{
        private int number = 1; //A:1  B:2  C:3
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private Condition condition2 = lock.newCondition();
        private Condition condition3 = lock.newCondition();
        public void prints(){
        lock.lock();
        try {
            //1.判断
            while(number != 1){
                condition.await();
            }
            //2干活
            for (int i=0; i<=4; ++i){
                System.out.println(Thread.currentThread().getName()+"\t"+number);
            }
            //3.通知
            number = 2;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

        public void prints2(){
        lock.lock();
        try {
            //1.判断
            while(number != 2){
                condition2.await();
            }
            //2干活
            for (int i=0; i<=9; ++i){
                System.out.println(Thread.currentThread().getName()+"\t"+number);
            }
            //3.通知
            number = 3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

        public void prints3(){
        lock.lock();
        try {
            //1.判断
            while(number != 3){
                condition3.await();
            }
            //2干活
            for (int i=0; i<=14; ++i){
                System.out.println(Thread.currentThread().getName()+"\t"+number);
            }
            //3.通知
            number = 1;
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class CondittionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
       for (int i=0; i<10 ;++i){
           new Thread(()->{
               shareData.prints();
           },"A").start();

           new Thread(()->{
               shareData.prints2();
           },"B").start();

           new Thread(()->{
               shareData.prints3();
           },"C").start();
       }
    }
}
