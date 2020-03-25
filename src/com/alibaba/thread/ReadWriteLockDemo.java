package com.alibaba.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author Mr.cai
 * @date 2020/3/24 - 21:18
 * 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是，如果一个线程想去写共享资源，就不应该再有其它线程可以对该资源进行读或写
 * 小总结:
 *        读-读能共享
 *        读-写不能共享
 *        写-写不能共享
 *
 *        写操作两个：原子+独占,中间整个过程必须是完成的统一的，不能被分割。打断
 *
 */
class MyCache{
    private volatile Map<String,Object> map = new HashMap<>();
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    public void put(String key, Object value) throws InterruptedException {
        reentrantReadWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在写入："+key);
            TimeUnit.SECONDS.sleep(3);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }

    public Object get(String key) throws InterruptedException {
        reentrantReadWriteLock.readLock().lock();
        Object result = null;
        try {
            System.out.println(Thread.currentThread().getName()+"\t正在读取："+key);
            TimeUnit.SECONDS.sleep(3);
            result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取完成"+result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            reentrantReadWriteLock.readLock().unlock();
        }
        return result;
    }

}
public class ReadWriteLockDemo {
    public static void main(String[] args) {

        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int temp = i;
            new Thread(()->{
                try {
                    myCache.put(temp+"",temp+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        for (int j = 0; j < 5; j++) {
            final int temp1 = j;
            new Thread(()->{
                try {
                    Object object = myCache.get(temp1 + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(j)).start();
        }
    }
}
