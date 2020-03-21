package com.alibaba.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Mr.cai
 * @date 2020/3/17 - 19:34
 */
//资源类=实例变量+实例方法
class Ticket{
    private int number = 30;
    //ReentrantLock:可重入锁
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (number >0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第"+(number--)+"\t还剩下"+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class ThreadDemo {

    /**
     * 题目:三个售票员  卖出  30张票
     * 笔记：如何编写企业级的多线程代码
     * 固定的编程套路+模板是什么
     * 1.在高内聚低耦合的前提下，
     * 线程   操作  资源类!!!!
     * 1.1一言不接，先创建一个资源类
     *
     */
        //主线程，一切程序的入口
        //线程操作资源类
        //线程的三种构造方法
        //Thread(Runnable target,String name)
        //Thread()
        //Thread(Runnable target)
        //线程的状态：NEW/RUNNABLE/BLOCKED/WAITING(死等)/TIMED_WAITING（过时不等）/TERMINATED
        public static void main(String[] args) {
            Ticket ticket = new Ticket();
            //new Runnable就相当于实现了runnable接口
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=1; i<=40; ++i){
                        ticket.sale();
                    }
                }
            }, "A").start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=1; i<=40; ++i){
                        ticket.sale();
                    }
                }
            }, "B").start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i=1; i<=40; ++i){
                        ticket.sale();
                    }
                }
            }, "C").start();

            //lamda表达式来定义线程
            new Thread(()->{
                for (int i=0; i<=40; ++i){
                    ticket.sale();
                }
            },"A").start();
        }
}
