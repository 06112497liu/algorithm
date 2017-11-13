package com.bbd;

import org.junit.Test;

public class Demo1 {

    public static void main(String[] args) {
        Long result = factorial(50L);
        System.out.println(result);

        long result2 = combination(4, new long[]{1, 2, 3, 4});
        System.out.println(result2);
    }

    /**
     * 输入 n,求 n!（0! = 1，负数没有阶乘）
     */
    private static long factorial(long n) {
        if(n < 0) throw new IllegalArgumentException("负数没有阶乘！");
        return n > 1 ? n*factorial(n-1) : 1;
    }

    /**
     * 根据给定的数字，计算出能组合出多少个不同的几位数
     * @param digits 几位数
     * @param ags 数字
     * @return
     */
    private static long combination(long digits, long... ags) {
        long result = factorial(digits) * factorial(ags.length) / ((factorial(digits)) * (factorial(ags.length - digits)));
        return result;
    }

    @Test
    public void combination2() {
        int i, j, k;
        int m=0;
        for(i=1;i<=4;i++)
            for(j=1;j<=4;j++)
                for(k=1;k<=4;k++){
                    if(i!=j&&k!=j&&i!=k){
                        System.out.println(""+i+j+k);
                        m++;
                    }
                }
        System.out.println("能组成："+m+"个");
    }

    private static void fileCopy(String path1, String path2) {
        
    }

}
