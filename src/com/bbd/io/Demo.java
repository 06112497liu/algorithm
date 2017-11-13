package com.bbd.io;

import org.junit.Test;

import java.io.*;

public class Demo {

    @Test
    public void test01() throws IOException {
        FileInputStream in = new FileInputStream("E://a.txt");
        FileOutputStream out = new FileOutputStream("E://b.txt");
        int b;
        int len = 0;
        while ((b = in.read()) != -1) {
            len++;
            out.write(b);
        }
        System.out.println(len);
    }

    @Test
    public void test02() throws IOException {
        FileReader in = new FileReader("E://a.txt");
        FileWriter out = new FileWriter("E://b.txt");
        int b;
        int len = 0;
        while ((b = in.read()) != -1) {
            len++;
            out.write(b);
            out.flush();
        }
        System.out.println(len);
    }

    @Test
    public void test03() throws IOException {
        FileInputStream in = new FileInputStream("E://a.txt");
        DataInputStream dataIn = new DataInputStream(in);
        boolean s = dataIn.readBoolean();
        System.out.println(s);
        char c = dataIn.readChar();
        System.out.println(c);
    }
}
