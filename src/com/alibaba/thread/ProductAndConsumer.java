package com.alibaba.thread;

/**
 * @author Mr.cai
 * @date 2020/3/18 - 15:59
 */
class Factory{
    private int num = 0;
    public synchronized void product(){
        while(num==0){
            num ++;
        }
    }

    public synchronized void consume(){

    }
}

public class ProductAndConsumer {

    public static void main(String[] args) {

    }
}
