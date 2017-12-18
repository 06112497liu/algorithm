package com.bbd.multithread;

import javafx.scene.paint.Stop;

import javax.sound.midi.Soundbank;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        //m1();
        //m2();
        //m3();
        //m4();
        //m5();
        m6();
    }

    public static void m1() {
        for (int i=0; i<100; i++) {
            new Thread(() -> CalNum.add(1L)).start();
            System.out.println(CalNum.count);
        }
    }

    public static  void m2() {
        for (int i=0; i<100; i++) {
            new Thread(() -> CalNum.add(1)).start();
            System.out.println(CalNum.atomicLong.get());
        }
    }

    public static  void m3() {
        for (int i=0; i<100; i++) {
            new Thread(() -> CalNum.add1(1L)).start();
            System.out.println(CalNum.count1);
        }
    }

    public static void m4() throws InterruptedException {
        new Thread(){
            @Override
            public void run() {
                Init.inited();
                Init.init.print();
            }
        }.start();

    }

    public static void m5() {
        System.out.println(1);
        System.out.println(2);
        System.out.println(3);
        System.out.println(4);
        System.out.println(5);
        System.out.println(6);
        System.out.println(7);
    }

    public static void m6() throws InterruptedException {
        myThread t1 = new myThread();
        new Thread(t1).start();
        Thread.sleep(100);
        t1.stop();
    }
}

class myThread implements Runnable {

    public volatile Boolean flag = false;

    @Override
    public void run() {
        flag = false;
        while (!flag) {

            System.out.println("线程执行中。。。");
        }
    }

    public void stop() {
        flag = true;
        System.out.println("停止");
    }
}
