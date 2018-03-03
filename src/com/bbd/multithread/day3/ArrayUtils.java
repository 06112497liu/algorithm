package com.bbd.multithread.day3;

public class ArrayUtils {

    public static void swap(int[] array, int cursor, int i) {
        int a = array[cursor];
        int temp = a;
        int b = array[i];
        array[cursor] = b;
        array[i] = temp;
    }

}
