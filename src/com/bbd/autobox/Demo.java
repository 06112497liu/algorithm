package com.bbd.autobox;

public class Demo {
    static int i = 5;
    static {
        i = 0;
        System.out.println(i);
    }
    public static void main(String[] args) {
        Integer a = 5;
        Integer b = 5;
        System.out.println(a == b);

        Integer c = 255;
        Integer d = 255;
        System.out.println(c == d);
    }
}
