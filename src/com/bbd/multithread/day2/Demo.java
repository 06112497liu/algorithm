package com.bbd.multithread.day2;

/**
 * @author Liuweibo
 * @version Id: Demo.java, v0.1 2018/3/2 Liuweibo Exp $$
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        T1 r1 = new T1('A', 'Z');
        T2 r2 = new T2(1, 52);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        System.out.println("结束");
    }
}

class LockObj {
    public static final Object lock1 = new Object();
    public static boolean flag = false;
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
            for (int i = (int)a; i <= (int)b; i++) {
                if (!LockObj.flag) {
                    try {
                        LockObj.lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
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
            for (int i = from; i <= to; i++) {
                System.out.println(i);
                LockObj.flag = true;
                if (i % 2 == 0) {
                    LockObj.lock1.notifyAll();
                    try {
                        LockObj.lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            LockObj.lock1.notifyAll(); // 防止打印字母的线程一直处于等待状态
        }
    }
}
    
    