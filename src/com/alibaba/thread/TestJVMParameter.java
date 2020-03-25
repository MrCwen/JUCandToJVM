package com.alibaba.thread;

/**
 * @author Mr.cai
 * @date 2020/3/21 - 19:32
 */
public class TestJVMParameter {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);

        byte[] aByte = new byte[100*100*100*1000];
    }
}
