package com.alibaba.thread;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.cai
 * @date 2020/3/25 - 22:57
 */
public class WeakReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        //内存从充足的情况下
        WeakReference weakReference = new WeakReference(1L);
        System.out.println(weakReference.get());

        Object o = new Object();
        o = null;
        System.gc();
        System.out.println("=======");
        System.out.println(weakReference);
    }
}
