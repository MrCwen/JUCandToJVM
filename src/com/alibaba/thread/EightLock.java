package com.alibaba.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.cai
 * @date 2020/3/17 - 22:11
 *
 * 线程睡眠的方式：
 * 1.TimeUnit：枚举，可以根据时分秒来进行睡眠
 * TimeUnit.SECONDS.sleep(4);
 *
 * 2.Thread.Sleep();
 *
 *
 * 1.标准访问，不确定先打印邮件还是先发送短信
 *
 * 2.暂停4秒钟在邮件方法，
 *
 * 3.新增普通sayHello方法
 *
 *4.
 */

class Phone{
    public static synchronized void sendEmail()throws Exception{
        TimeUnit.SECONDS.sleep(4);
        System.out.println("send");
    }

    public synchronized void sendMessage()throws Exception{
        System.out.println("send");
    }

    public void sayHello() throws Exception{
        System.out.println("aaa");
    }
}
public class EightLock {
    public static void main(String[] args) throws InterruptedException {
        //多线程，线程的调度不是从上到下，而是跟CPU有关
        Phone phone = new Phone();
        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"A");
        new Thread(()->{
            try {
                phone.sayHello();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"B");
    }
}
