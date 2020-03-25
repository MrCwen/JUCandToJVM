package com.alibaba.thread;

/**
 * @author Mr.cai
 * @date 2020/3/25 - 12:12
 */
class Data{
    int data = 0;
    public void  get(){
        data--;
    }
    public void set(){
        data++;
    }
}
public class TranditionalProducerAndConsumer {
    static Data data = new Data();
    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                synchronized (data){
                    while(data.data!=0){
                        try {
                            data.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    data.set();
                    System.out.println(Thread.currentThread().getName()+"生产了:"+data.data);
                    data.notifyAll();
                }
            },String.valueOf(i)).start();

            new Thread(()->{
                synchronized (data){
                    while(data.data==0){
                        try {
                            data.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    data.get();
                    System.out.println(Thread.currentThread().getName()+"获取到了:"+data.data);
                    data.notifyAll();
                }
            },String.valueOf(i)).start();
        }
    }
}
