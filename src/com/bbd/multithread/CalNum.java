package com.bbd.multithread;

import java.util.concurrent.atomic.AtomicLong;

public class CalNum {

    public static Long count = 0L;

    public static AtomicLong atomicLong = new AtomicLong(0);

    public static volatile Long count1 = 0L;

    public static Long add(Long num) {
        count = count + num;
        return count;
    }

    public static Long add(Integer num) {
        long rs = atomicLong.incrementAndGet();
        return rs;
    }

    public static Long add1(Long num) {
        count1 = count1 + num;
        return count1;
    }
}
