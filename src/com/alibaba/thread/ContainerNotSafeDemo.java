package com.alibaba.thread;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Mr.cai
 * @date 2020/3/24 - 18:49
 * ArrayList线程不安全
 */
public class ContainerNotSafeDemo {
    public static void main(String[] args) {
        //List<String> list = new ArrayList<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        list.forEach(System.out::println);
        for (int i = 0; i <= 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                //java.util.ConcurrentModificationException
                System.out.println(list);
            },String.valueOf(i)).start();
        }
        /**
         * 导致原因：
         * 并发争抢修改导致，参考我们的花名册前面情况
         * 一个人正在写入，另外一个学生过来抢着写，最终导致数据不一致
         *
         *
         * 解决方案：
         * 1.添加synchronized同步代码块
         * 2.使用List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3.使用vector类
         * 4.copyonWrite：写时复制
         * List<String> list = new CopyOnWriteArrayList<>();
         *
         * 写时复制：读写分离的思想：
         * 每次拿到锁之后，使用Arrays.copyof复制原来的数组，长度+1，然后在最后的元素
         * 写入要写的值，然后释放锁
         *
         * 优化建议：
         *
         *
         *
         */

        /**
         * HashSet类似，线程不安全
         * 解决方案：
         * 1.Collections.
         * 2.copyonWriteHashSet,底层使用的copyonwriteArrayList
         *
         * HashSet底层是HashMap，key为static final new Object()
         */
        Set<String> set = new HashSet<>();


        /**
         * HashMap:线程不安全，解决方式ConcurrentHashMap
         */
    }
}
