package com.alibaba.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Mr.cai
 * @date 2020/3/25 - 10:28
 * ArrayBlockingQueue: 是一个基于数组结构的有界阻塞队列，此队列按照FIFO(先进先出)原则对元素进行排序
 * LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO(先进先出)排序元素，吞吐量通常高于ArrayBlockingQueue
 * SynchronousQueue： 一个部存储元素的阻塞队列，每个插入操作必须等到另一个线程调用移除操作，否则插入操作会一直处于阻塞状态
 * 吞吐量要高
 * 1.队列
 *
 * 2.阻塞队列
 * 2.1阻塞队列有没有好的一面
 *
 * 2.2不得不阻塞，你如何管理
 */
public class BlockQueueDemo {
    public static void main(String[] args) throws InterruptedException {
      //List list = new ArrayList();
        //设置队列的大小
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
//        System.out.println(queue.add("a"));
//        System.out.println(queue.add("b"));
//        System.out.println(queue.add("c"));

        //当超出队列大小限制的时候会抛出异常,当阻塞队列满的时候，再往队列里面
        //add元素会抛出下面异常
        //java.lang.IllegalStateException
        //System.out.println(queue.add("d"));

//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        System.out.println(queue.remove());
//        //当队列中没有元素的时候，移除元素会报java.util.NoSuchElementException
        //System.out.println(queue.remove());

        //没有元素会报java.util.NoSuchElementException
//        queue.element();

        /*System.out.println(queue.offer("c"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("c"));
        //另外一种插入方式
        System.out.println(queue.offer("c"));

        System.out.println(queue.peek());
        System.out.println(queue.peek());
        System.out.println(queue.peek());
        System.out.println(queue.peek());

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());*/
      /*  queue.put("a");
        queue.put("a");
        queue.put("a");
        //当队列慢的时候，继续往队列里put元素，队列会一直阻塞直到put数据或者响应中断退出
        //queue.put("a");

        queue.take();
        queue.take();
        queue.take();
        //当队列没有可以取得队列时，队列会一直阻塞
        queue.take();*/

        System.out.println(queue.offer("a",2l, TimeUnit.SECONDS));
        System.out.println(queue.offer("a",2l, TimeUnit.SECONDS));
        System.out.println(queue.offer("a",2l, TimeUnit.SECONDS));
        //当队列满的时候，继续往里面插入元素，会阻塞两秒，还是无法插入，退出阻塞状态
        System.out.println(queue.offer("a",2l, TimeUnit.SECONDS));
    }

}
