package com.alibaba.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Mr.cai
 * @date 2020/3/17 - 21:58
 *
 * HashMap也是线程不安全的类：
 * java.util.ConcurrentModificationException
 * 解决方式：
 * ConcurrentHashMap();
 *
 *
 */
public class UnsafeMap {
    public static void main(String[] args) {
        //会出现线程不安全的问题
        //Map<String,String> map = new HashMap();

        //解决方式：使用并发map
        Map map = new ConcurrentHashMap();
        for (int i=0; i<=4; ++i){
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
}
