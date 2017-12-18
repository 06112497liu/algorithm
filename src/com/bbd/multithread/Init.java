package com.bbd.multithread;

public class Init {

    public static volatile Init init = null;

    public static Init inited() {
        init = new Init();
        return init;
    }

    public void print() {
        System.out.println("初始化了吗？");
    }
}
