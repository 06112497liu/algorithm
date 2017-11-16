package com.bbd.adapterPattern.classAdapter;

/**
 * 适配器类
 * @author Liuweibo
 * @version Id: Adapter.java, v0.1 2017/11/14 Liuweibo Exp $$
 */
public class Adapter extends Adaptee implements Target {


    @Override
    public void m2() {
        System.out.println("方法m2（）。。。。");
    }
}
    
    