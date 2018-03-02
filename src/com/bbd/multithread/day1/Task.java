package com.bbd.multithread.day1;

/**
 * @author Liuweibo
 * @version Id: Task.java, v0.1 2018/2/28 Liuweibo Exp $$
 */
public class Task implements Runnable {

    boolean flag = true;
    int i = 0;

    @Override
    public void run() {
        while (flag) {
            i++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread t1 = new Thread(task);
        t1.start();
        Thread.sleep(10);
        task.flag = false;
        Thread.sleep(100);
        System.out.println(task.i);
        System.out.println("程序停止");
    }
}
    
    