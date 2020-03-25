package com.alibaba.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author Mr.cai
 * @date 2020/3/24 - 21:51
 */
public class CountDownLatchDemo {
    private static int NUM = 6;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(NUM);
        for (int i = 0; i <6 ; i++) {
            new Thread(()->{
                System.out.println("同学离开教室");
                countDownLatch.countDown();
            },CountyEnum.foreach_CountryEnum(i).getRetMessage()).start();
        }
        countDownLatch.await();
        System.out.println("班长离开教室");
    }
}
