package com.alibaba.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.cai
 * @date 2020/3/25 - 12:50
 */
class ShareData2{
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    int num = 0;
    public void printC(){
        lock.lock();
        try {
            while(num !=0) condition.await();
            for (int i = 0; i < 5; i++) {
                System.out.println("cccc");
            }
            num++;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printD(){
        lock.lock();
        try {
            while(num !=1) condition1.await();
            for (int i = 0; i < 5; i++) {
                System.out.println("dddd");
            }
            num++;
            condition2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void printE(){
        lock.lock();
        try {
            while(num !=2) condition2.await();
            for (int i = 0; i < 5; i++) {
                System.out.println("eeee");
            }
            num=0;
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class WakeupCorreetOne {
    public static ShareData2 shareData2 = new ShareData2();
    public static void main(String[] args) {
        new Thread(()->{
            shareData2.printC();
        },"AAA").start();
        new Thread(()->{
            shareData2.printD();
        },"BBB").start();
        new Thread(()->{
            shareData2.printE();
        },"CCC").start();
    }
}
