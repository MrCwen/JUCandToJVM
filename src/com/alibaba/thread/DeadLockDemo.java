package com.alibaba.thread;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;

/**
 * @author Mr.cai
 * @date 2020/3/25 - 18:12
 * 死锁：死锁是两个或两个以上线程在执行过程中，因争夺
 *      资源而造成的一种互相等待现象，若无外力干涉那他们都将无法推进下去
 */
class HoldLockThread implements Runnable{
    private String LockA;
    private String LockB;
    public HoldLockThread(String lockA, String lockB) {
        LockA = lockA;
        LockB = lockB;
    }


    @Override
    public void run() {
        synchronized (LockA){
             try {
                 System.out.println("这个线程自己持有锁"+LockA+"尝试获得"+LockB);
                 TimeUnit.SECONDS.sleep(2);
                 synchronized (LockB){
                     System.out.println("这个线程自己持有锁"+LockB+"尝试获得"+LockA);
                 }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        String LockA = "LockA";
        String LockB = "LockB";
        new Thread(
            new HoldLockThread(LockA,LockB)
        ,"AA").start();

        new Thread(
            new HoldLockThread(LockB,LockA),"BB").start();
    }

    /**
     * linux ps -ef |grep XXX :查看当前运行的进程
     * windows下的java运行程序，也有类似ps的查看进程的命令，但是目前我们需要查看的
     * 只是java
     * 步骤：
     * 1.jps = java ps        jps -l;定位进程号
     * 2.jstack 端口号              ;找到死锁查看
     */
}
