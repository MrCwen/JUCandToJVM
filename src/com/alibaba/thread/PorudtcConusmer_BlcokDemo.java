package com.alibaba.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Mr.cai
 * @date 2020/3/25 - 13:32
 * volatile/cas/atomicInteger/BlcokQueue/线程交互/原子引用
 */
class MyResource{
    private volatile boolean FLAG = true;//默认开启，进行生产和消费
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProdu() throws Exception{
        String data = "";
        boolean retVal = true;
        while (FLAG){
            data = atomicInteger.incrementAndGet()+"";
            retVal = blockingQueue.offer(data,2l, TimeUnit.SECONDS);
            if(retVal){
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1l);
        }
        System.out.println(Thread.currentThread().getName()+"生产结束");
    }

    public void myConsume()throws Exception{
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2l, TimeUnit.SECONDS);
            if(null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t超过两秒没有取到数据，退出");
                return;
            }
            System.out.println(Thread.currentThread().getName()+"\t消费队列"+result+"成功");

        }
    }

    public void stop(){
        this.FLAG = false;
    }
}
public class PorudtcConusmer_BlcokDemo {
    public static void main(String[] args) throws InterruptedException {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"生产线程启动");
            try {
                myResource.myProdu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"消费者线程启动");
            try {
                myResource.myConsume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consume").start();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("main线程叫停，活动结束");
        myResource.stop();
    }
}
