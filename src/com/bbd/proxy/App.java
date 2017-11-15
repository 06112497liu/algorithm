package com.bbd.proxy;

import org.junit.Test;

public class App {

    @Test
    public void test01() {
        UserService userService = new UserServiceImpl();
        UserProxy1 userProxy = new UserProxy1();
        userProxy.setObject(userService);
        UserService proxy = (UserService)userProxy.getProxy();
        proxy.save();
    }
}
