package com.bbd.multithread.day2;

/**
 * @author Liuweibo
 * @version Id: Demo.java, v0.1 2018/3/2 Liuweibo Exp $$
 */
public class Demo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new T1('A', 'Z'));
        Thread t2 = new Thread(new T2(1, 53));
        t1.setPriority(10);
        t1.start();
        t2.start();
    }
}

class LockObj {
    public static final Object lock1 = new Object();
}

class T1 implements Runnable {

    char a;
    char b;

    public T1(char a, char b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        synchronized (LockObj.lock1) {
            for (int i = (int)a; i < (int)b; i++) {
                System.out.println((char) i);
                LockObj.lock1.notifyAll();
                try {
                    LockObj.lock1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class T2 implements Runnable {

    int from;
    int to;

    public T2(int from, int to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public void run() {
        synchronized (LockObj.lock1) {
            for (int i = from; i < to; i++) {
                System.out.println(i);
                if (i % 2 == 0) {
                    LockObj.lock1.notifyAll();
                    try {
                        LockObj.lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
    
    