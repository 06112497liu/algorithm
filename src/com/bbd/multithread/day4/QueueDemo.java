package com.bbd.multithread.day4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class QueueDemo {
    public static void main(String[] args) {
        /**
         * 创建的非公平阻塞队列，当队列可用时，阻塞的线程将进入争夺访问资源的竞争中，也就是说谁先抢到谁就执行，没有固定的先后顺序。
         * ArrayBlockingQueue(int capacity, boolean fair)这个构造方法根据fair灵活创建公平还是非公平队列，true-公平队列；
         * 对于公平访问队列，被阻塞的线程可以按照阻塞的先后顺序访问队列，即先阻塞的线程先访问队列
         */
        final ArrayBlockingQueue<Apple> list = new ArrayBlockingQueue<Apple>(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        service.execute(new Comsumer(list));
        service.execute(new Comsumer(list));
        service.execute(new Comsumer(list));
        service.execute(new Comsumer(list));
        service.execute(new Producer(list));
    }
}

class Comsumer implements Runnable {

    private ArrayBlockingQueue<Apple> list;

    public Comsumer(ArrayBlockingQueue<Apple> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            comsume();
        }
    }

    private void comsume() {
        try {
            Apple apple = list.take(); // 移除组赛队列的头部元素，没有则一直等待
            System.out.println(Thread.currentThread().getName() + "--消费者消费一个苹果：" + apple);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {

    private ArrayBlockingQueue<Apple> list;

    public Producer(ArrayBlockingQueue<Apple> list) {
        this.list = list;
    }

    @Override
    public void run() {
        int i = 0;
        while (true) {
            produce(i);
            i++;
        }
    }

    private void produce(int i) {
        Apple apple = new Apple(i);
        try {
            Thread.sleep(1000);
            list.put(apple); // 向阻塞队列尾部添加一个元素
            System.out.println(Thread.currentThread().getName() + "--生产者生产一个苹果:" + apple);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Apple {
    private int i;
    public Apple(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "i=" + i +
                '}';
    }
}
