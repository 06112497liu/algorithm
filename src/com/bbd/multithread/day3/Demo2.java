package com.bbd.multithread.day3;

import java.util.Arrays;

public class Demo2 {

    private static void fullArray(int[] array, int cursor, int end) {
        if (cursor == end) {
            System.out.println(Arrays.toString(array));
        } else {
            for (int i = cursor; i <= end; i++) {
                ArrayUtils.swap(array, cursor, i);
                fullArray(array, cursor + 1, end);
                ArrayUtils.swap(array, cursor, i); // 用于对之前交换过的数据进行还原
            }
        }
    }

    private static void core(int[] array) {
        int length = array.length;
        fullArray(array, 0, length - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2};
        core(arr);
    }

}
