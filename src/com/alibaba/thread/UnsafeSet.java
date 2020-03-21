package com.alibaba.thread;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Mr.cai
 * @date 2020/3/17 - 21:43
 *
 * Hashset底层：hashMap！！！！
 * HashSet的add方法的key为：set方法存放进去的那个值，value为new Object（）常量
 *
 * Set：多线程并发访问的时候，可能会出现 java.util.ConcurrentModificationException异常
 *
 * 解决方式：
 * JUC包下的，使用写时复制类
 *Set set = new CopyOnWriteArraySet();
 *
 *
 *
 */
public class UnsafeSet {
    public static void main(String[] args) {
        //线程不安全：会出并发修改异常
        //Set set = new HashSet();
        //解决方式1：使用写时复制
        Set set = new CopyOnWriteArraySet();
        for (int i=0; i<4;++i) new Thread(()->{
            set.add(UUID.randomUUID().toString().substring(0,8));
            System.out.println(set);
        },"A").start();
    }
}
