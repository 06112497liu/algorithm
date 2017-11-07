package com.bbd;

public class Demo1 {

    public static void main(String[] args) {
        Long result = factorial(50L);
        System.out.println(result);
    }

    /**
     * 输入 n,求 n!（0! = 1，负数没有阶乘）
     */
    private static long factorial(long n) {
        if(n < 0) throw new IllegalArgumentException("负数没有阶乘！");
        return n > 1 ? n*factorial(n-1) : 1;
    }
}
