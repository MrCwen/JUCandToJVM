package com.alibaba.thread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Mr.cai
 * @date 2020/3/17 - 20:41
 * ArrayList :初始值为Object类型的数组，长度为10
 * 扩容机制：第一次扩容会扩到15，为上一次数组大小的一半。10 + 10/2 = 15
 *          第三次会扩容上一次的一半取整：15 + 15/2取整 = 22
 * 扩容的方式：拷贝第一次的10个元素到另外一个15个大小的地方，拷贝的方式Arrays.copyof
 *            即是，将上一次的所有元素拷贝到新开辟的新大小的数组上
 *
 *
 * ArrayList线程不安全的原因：下面的例子,可能会报这个异常
 * java.util.ConcurrentModificationException（并发修改异常）
 * 多线程并发操作一个类，且没有加锁。
 *
 *
 * 解决方法：
 *1.add方法加上synchronized
 *2.使用vector，因为verctor的add加上了synchronized，但是不建议。同一个时间段，只能有一个线程来操作这个方法。
 *3.List<String> list = Collections.synchronizedList(new ArrayList<>());
 *4.List<String> list = new CopyOnWriteArrayList<>();
 * 写时复制：往一个容器添加元素的时候，不直接往Object[]添加，而是先将当前容器Object[]
 * 进行copy，复制出一个新的Object[] newElements,然后往新的容器Object[] newElements里面添加
 * 元素，添加完元素之后，再将源容器的引用执行新的容器setArray(newEelements);这样做的好处是对
 * copyonwrite容器进行并发的读，而不需要加锁，因为当前元素不会添加任何元素。所以copyonwrite容器也是一种
 * 读写分离的思想，读和写不同的容器
 *
 *
 *
 *
 *
 * HashMap：初始值为16
 * 扩容机制：每次扩容都是上次的一倍，第一次扩容后的大小32
 *
 */
public class UnsafeDemo {
    public static void main(String[] args) {
        //List<String> list = new ArrayList<>();
        //方法0：List的add方法上加上synchronized
        //方法1：
        //Vector vector = new Vector();
        //方法2：
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        //方法3：写时复制  JUC包下的类
        //List<String> list = new CopyOnWriteArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i=0; i<=3;i++) new Thread(()->{
            //java.util.ConcurrentModificationException
            list.add(UUID.randomUUID().toString().substring(0,8));
            System.out.println(list);
        },String.valueOf(i)).start();

    }
}
