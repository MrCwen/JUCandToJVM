package com.alibaba.thread;
/**
 * @author Mr.cai
 * @date 2020/3/17 - 20:09
 *
 * 1.函数式接口编程
 *  Lambda表达式（解决匿名内部类的操作）：函数式接口，里面的方法有且只能有一个
 * (1)拷贝中括号，写死右箭头，落地大括号
 * (2)@FunctionalInterface,如果接口的方法是一个，默认加上了这个注解，即可以使用lambDa表达式
 * (3)接口里面可以有default修饰的具体实现的方法，可以有多个
 * (4)接口里面可以有static修饰的具体实现的方法，可以有多个
 */
@FunctionalInterface
interface Foo{
    //public void sayHELLO();
    public int add(int x,int y);

    public default int mul(int x,int y){
        return x*y;
    }

    public default int mul2(int x,int y){
        return x*y;
    }

    public static int div(int x,int y){
        return x/y;
    }
}
public class LambdaExpressionDemo {
    public static void main(String[] args) {
        Foo foo = (int x,int y)->{
            System.out.println(x+y);
            return x+y;
        };

        foo.add(1,2);
        foo.mul(1,2);
        Foo.div(2,1);


        new Thread(()->{for (int i=0;i <40; ++i) foo.add(1,2);},"A").start();
    }
}
