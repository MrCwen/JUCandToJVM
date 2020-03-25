package com.alibaba.thread;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Mr.cai
 * @date 2020/3/24 - 17:59
 */
class  User{
    String name;
    int age;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
public class AtomicReferenceDemo {
    public static void main(String[] args) {
        AtomicReference<User> atomicReference = new AtomicReference<>();
        User user1 = new User("z3",22);
        User user2 = new User("lisi",22);
        atomicReference.set(user1);
        boolean b = atomicReference.compareAndSet(user1, user2);
        boolean c = atomicReference.compareAndSet(user1, user2);
        System.out.println(b+""+atomicReference.get().toString());
        System.out.println(c+""+atomicReference.get().toString());
    }
}
