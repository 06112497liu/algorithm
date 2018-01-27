package com.bbd.multithread;

/**
 * @author Liuweibo
 * @version Id: NoVisibility.java, v0.1 2017/12/19 Liuweibo Exp $$
 */
public class NoVisibility {

    private static boolean ready;

    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready)
                    Thread.yield();
                System.out.println(number);
        }
    }

    public static void main(String[] args) {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

}
    
    