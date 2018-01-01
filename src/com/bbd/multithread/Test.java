package com.bbd.multithread;

import javafx.scene.paint.Stop;

import javax.sound.midi.Soundbank;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Test {

    public static void main(String[] args) throws InterruptedException, IOException {
        //m1();
        //m2();
        //m3();
        //m4();
        //m5();
        //m6();
        //m7();
        m8();
    }
    public static void m8() throws IOException, InterruptedException {
        Thread t[] = new Thread[2];
        Thread.State s[] = new Thread.State[2];
        for (int i=0; i<2; i++) {
            t[i] = new Thread(new Calculator(i));
            if ((i%2) == 0) {
                t[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                t[i].setPriority(Thread.MIN_PRIORITY);
            }
            t[i].setName("Thread" + i);
        }
        FileWriter file = new FileWriter("G://record.txt");
        PrintWriter pw = new PrintWriter(file);
        for (int i=0; i<2; i++) {
            pw.println("Main : Status of Thread "+i+" : " +t[i].getState());
            pw.flush();
            s[i]=t[i].getState();
        }
        for (int i=0; i<2; i++) {
            t[i].start();
        }
        boolean finish=false;
        while (!finish) {
            for (int i=0; i<2; i++){
                if (t[i].getState()!=s[i]) {
                    writeThreadInfo(pw, t[i],s[i]);
                    s[i]=t[i].getState();
                }
            }
            finish=true;
            for (int i=0; i<2; i++){
                finish=finish && (s[i] == Thread.State.TERMINATED);
                if (finish == false) {
                    break;
                }
            }
        }
        for (int i=0; i<2; i++) {
            System.out.println(t[i].getName() + ":" + t[i].getState());
            System.out.println(t[i].getName() + ":" + s[i]);
        }
    }
    private static void writeThreadInfo(PrintWriter pw, Thread thread, Thread.State state) {
        pw.printf("Main : Id %d - %s\n", thread.getId(), thread.getName());
        pw.println();
        pw.printf("Main : Priority: %d\n", thread.getPriority());
        pw.println();
        pw.printf("Main : Old State: %s\n", state);
        pw.println();
        pw.printf("Main : New State: %s\n", thread.getState());
        pw.println();
        pw.flush();
    }
        public static void m7() {
        for (int i=0; i<10; i++) {
            Calculator calculator = new Calculator(i);
            Thread t = new Thread(calculator);
            t.start();
        }
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
