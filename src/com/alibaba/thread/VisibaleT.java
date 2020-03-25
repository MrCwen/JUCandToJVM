package com.alibaba.thread;

import java.util.Collections;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.cai
 * @date 2020/3/21 - 21:38
 */
class Run{
    int num = 10;
    public void add(){
        this.num = 11;
    }
}
public class VisibaleT {
    public static void main(String[] args) throws InterruptedException {
        Run run = new Run();
        new Thread(()->{
            //这里是为了让线程睡三秒钟，让main线程运行到while处
            //如果满足可见性，线程改了num，while处的主线程应该可以查看到
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            run.add();
        },"A").start();
        System.out.println(run.num);
        while (run.num == 10){
        }
    }
}
