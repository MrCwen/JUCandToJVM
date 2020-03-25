package com.alibaba.thread;

/**
 * @author Mr.cai
 * @date 2020/3/21 - 16:05
 *
 * 如果是JDK自带的走的是BootStrapClassLoader
 * 如果是自己定义的类，走的是ApplicationClassLoader
 *
 * 有下面可以看出：
 * 根类加载器-->扩展类加载器（根类加载器的子类）-->应用程序类加载器-->用户自定义类加载器
 */
public class JVMDemo {
    public static void main(String[] args) {
        Object object = new Object();
        ClassLoader classLoader = object.getClass().getClassLoader();
        System.out.println(classLoader);//null



        //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(JVMDemo.class.getClassLoader());
        //sun.misc.Launcher$ExtClassLoader@1b6d3586
        System.out.println(JVMDemo.class.getClassLoader().getParent());
        //null
        System.out.println(JVMDemo.class.getClassLoader().getParent().getParent());
    }
}
