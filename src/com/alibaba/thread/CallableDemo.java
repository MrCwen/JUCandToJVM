package com.alibaba.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Mr.cai
 * @date 2020/3/21 - 15:12
 * 创建线程的四种方式：
 * 1.继承Tread类
 * 2.实现Runnable接口
 * 3.实现callable接口，创建callable接口的子类，futureTask类，构造方法传递callable接口
 * 4.线程池
 */
class Mythread implements Runnable{

    @Override
    public void run() {

    }
}

/**
 * callable接口
 * 1.run方法的返回值的泛型为run方法的返回值
 * 2.
 */
class  MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("callable");
        return 1024;
    }


}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask(new MyThread2());
        new Thread(futureTask,"A").start();
        Integer result = futureTask.get();
    }
}
