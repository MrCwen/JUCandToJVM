package com.alibaba.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.cai
 * @date 2020/3/24 - 20:13
 */
//1.资源类
class Phone1 implements  Runnable{
    public synchronized void sendSMS()throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoiked sendMSM");
        sendEMail();
    }

    public synchronized void sendEMail()throws Exception{
        System.out.println(Thread.currentThread().getName()+"\t invoiked sendEmail");
    }

    @Override
    public void run() {
        try {
            get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Lock lock = new ReentrantLock();
    public void get() throws  Exception{
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"get");
            set();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void set() throws  Exception{
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"set");
           // get();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class ReentrateLockDemo {

    public static void main(String[] args) throws InterruptedException {
        Phone1 phone1 = new Phone1();
        new Thread(()->{
            try {
                phone1.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();

        new Thread(()->{
            try {
                phone1.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(phone1,"t3").start();
        new Thread(phone1,"t4").start();
        /**
         * t3get
         * t3set
         * t4get
         * t4set
         */
    }

    /**
     * 结果：
     * t1	 invoiked sendMSM
     * t1	 invoiked sendEmail
     * t2	 invoiked sendMSM
     * t2	 invoiked sendEmail
     *
     * synchronized是一个典型的可重入锁：
     * t1在进入sendSMS同步方法后，会自动进入SendEmail同步方法，可以自动获取锁
     */
}
