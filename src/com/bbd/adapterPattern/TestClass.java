package com.bbd.adapterPattern;

import com.bbd.adapterPattern.classAdapter.Adapter;
import com.bbd.adapterPattern.classAdapter.Target;
import org.junit.Test;

/**
 * @author Liuweibo
 * @version Id: TestClass.java, v0.1 2017/11/14 Liuweibo Exp $$
 */
public class TestClass {

    @Test
    public void test01() {
        Target t = new Adapter();
        t.m1();
        t.m2();
    }

}
    
    