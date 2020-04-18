package com.alibaba.thread;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author Mr.cai
 * @date 2020/3/26 - 12:16
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();


        System.out.println("============================");

        myWeakHashMap();
    }

    public static void myHashMap(){
        HashMap<Integer,String> hashMap = new HashMap();
        Integer key = new Integer(1);
        String value = "HashMap";

        hashMap.put(key,value);
        System.out.println(hashMap);

        key = null;
        System.out.println(hashMap);

        System.gc();
        System.out.println(hashMap+"===="+hashMap.size());
    }


    public static void myWeakHashMap(){
        WeakHashMap<Integer,String> hashMap = new WeakHashMap();
        Integer key = new Integer(1);
        String value = "HashMap";

        hashMap.put(key,value);
        System.out.println(hashMap);

        key = null;
        System.out.println(hashMap);

        System.gc();
        System.out.println(hashMap+"===="+hashMap.size());
    }

    public void myPhotomReference(){
        Object o1 = new Object();
        ReferenceQueue<Object> objectReferenceQueue = new ReferenceQueue<>();
        
    }
}
