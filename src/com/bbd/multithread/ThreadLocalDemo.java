package com.bbd.multithread;

import org.junit.Test;

/**
 * @author Liuweibo
 * @version Id: ThreadLocalDemo.java, v0.1 2017/12/21 Liuweibo Exp $$
 */
public class ThreadLocalDemo {

    Long aLong = 0L;
    String str = "0";

    public void set1() {
        aLong = Thread.currentThread().getId();
        str = Thread.currentThread().getName();
    }

    public Long getALong() {
        return aLong;
    }

    public String getStr() {
        return str;
    }

    ThreadLocal<Long> longLocal = new ThreadLocal<>();
    ThreadLocal<String> stringLocal = new ThreadLocal<>();

    public void set() {
        longLocal.set(Thread.currentThread().getId());
        stringLocal.set(Thread.currentThread().getName());
    }

    public long getLong() {
        return longLocal.get();
    }

    public String getString() {
        return stringLocal.get();
    }

    public static void main(String[] args) throws InterruptedException {

    }

    @Test
    public void method1() throws InterruptedException {
        final ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        threadLocalDemo.set();
        System.out.println("0:" + threadLocalDemo.getLong());
        System.out.println("0:" + threadLocalDemo.getString());

        Thread thread0 = new Thread(() -> {
            threadLocalDemo.set();
            System.out.println("1:" + threadLocalDemo.getLong());
            System.out.println("1:" + threadLocalDemo.getString());
        });
        thread0.start();
        thread0.join();
        System.out.println("2:" + threadLocalDemo.getLong());
        System.out.println("2:" + threadLocalDemo.getString());
    }

    @Test
    public void method2() throws InterruptedException {
        final ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        threadLocalDemo.set1();
        System.out.println("0:" + threadLocalDemo.getALong());
        System.out.println("0:" + threadLocalDemo.getStr());

        Thread thread0 = new Thread(() -> {
            threadLocalDemo.set1();
            System.out.println("1:" + threadLocalDemo.getALong());
            System.out.println("1:" + threadLocalDemo.getStr());
        });
        thread0.start();
        thread0.join();

        System.out.println("2:" + threadLocalDemo.getALong());
        System.out.println("2:" + threadLocalDemo.getStr());
    }
}


    
    