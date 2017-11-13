package com.bbd.io;

import java.io.*;

public class Demo03 {

    public static void main(String[] args) throws Exception
    {
        Demo03 t = new Demo03();
        t.write();
        t.read();
    }

    public void write() throws Exception
    {
        String path = "E://test.txt";
        OutputStream os = new FileOutputStream(path);
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeUTF("我好");
        dos.writeBoolean(true);
        dos.writeInt(1000);
        dos.writeInt(2000);
        dos.flush();
        os.close();
        dos.close();
    }

    public void read() throws Exception
    {
        InputStream instream = new FileInputStream("E://test.txt");
        DataInputStream dis = new DataInputStream(instream);
        double d = dis.readDouble();
        boolean b = dis.readBoolean();
        // 先写的先被读出来
        int i1 = dis.readInt();
        int i2 = dis.readInt();
        instream.close();
        dis.close();
        System.out.println(d);
        System.out.println(b);
        System.out.println(i1);
        System.out.println(i2);
    }
}
