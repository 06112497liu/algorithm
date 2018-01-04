package com.bbd.multithread;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorDemo {

    private ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Test
    public void test() {
        List<Future<String>> list = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Future<String> future = executorService.submit(new MyTask(i));
            list.add(future);
        }
        for (Future<String> f : list) {
            try {
                System.out.println(f.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                executorService.shutdown();
            }
        }
    }
}

class MyTask implements Callable {

    private int i;

    public MyTask(int i) {
        this.i = i;
    }

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName() + " 的call()方法被自动调用：" + i);
        return Thread.currentThread().getName() + "的call()方法返回的结果是：" + i;
    }
}