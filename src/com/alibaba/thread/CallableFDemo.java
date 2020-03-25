package com.alibaba.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Mr.cai
 * @date 2020/3/25 - 14:15
 */
class MyCall implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("callable come in");
        return 1024;
    }
}
public class CallableFDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //两个线程，一个main线程，一个futureTask线程
        //get获取callable线程的计算结果，如果没有计算完成会阻塞，直到计算完成
        //所以把get尽量往后面放
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyCall());
        Thread thread = new Thread(futureTask);
        thread.start();
        while(!futureTask.isDone()){}
        //建议放在最后
        Integer integer = futureTask.get();
        System.out.println(integer);
    }
}
