package com.bbd.multithread.day3;

import java.util.*;

public class Demo {

    public Set<List<String>> calWholePermutation(List<String> list) {
        Set<List<String>> rs = new HashSet<>();
        int size = list.size();
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                if (i != j) {
                    List<String> s = swap(list, i, j);
                    rs.add(s);
                    System.out.println(list);
                }
            }
        }
        return rs;
    }

    public List<String> swap(List<String> list, int p1, int p2) {
        List<String> rs = new LinkedList<>(list);
        String s1 = rs.get(p1);
        String s2 = rs.get(p2);
        if (!s1.equals(s2)) {
            rs.set(p1, s2);
            rs.set(p2, s1);
        }
        return rs;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A"); list.add("B"); list.add("C");
        Demo d = new Demo();
        Set<List<String>> rs = d.calWholePermutation(list);
        System.out.println(rs);
    }

}
