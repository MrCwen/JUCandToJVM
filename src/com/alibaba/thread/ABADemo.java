package com.alibaba.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author Mr.cai
 * @date 2020/3/24 - 18:15
 */
//ABA问题解决
public class ABADemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    //引入解决ABA问题,参数一：初始值 参数2：时间戳
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(100,1);
    //下面这两个现场调用start不一定是最前面的先执行
    public static void main(String[] args) {
        System.out.println("========以下是ABA问题的产生======");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();

        new Thread(()->{
        //暂停一秒，让t1先执行,保证上面的t1现场完成了一次ABA操作
            try {
                TimeUnit.SECONDS.sleep(2);
                boolean b = atomicReference.compareAndSet(100, 2019);
                System.out.println(b+""+atomicReference.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t2").start();
        System.out.println("=========一下是ABA问题的解决========");

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() +"第一次版本号"+stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
                atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName() +"第二次版本号"+atomicStampedReference.getStamp());
                atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName() +"第三次版本号"+atomicStampedReference.getStamp());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t3").start();

        new Thread(()->{
            try {
                int stamp = atomicStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName() +"第一次版本号"+stamp);
                //暂停三秒，保证上面的t3完成ABA操作
                TimeUnit.SECONDS.sleep(3);
                boolean b = atomicStampedReference.compareAndSet(100, 2019, stamp, stamp + 1);
                System.out.println(Thread.currentThread().getName() +"修改成功否----"+b);
                System.out.println(Thread.currentThread().getName() +"实际值"+atomicStampedReference.getReference());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t4").start();
    }
}
