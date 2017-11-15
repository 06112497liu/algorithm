package com.bbd.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class UserProxy1 {

    private Object object;

    public void setObject(Object object) {
        this.object = object;
    }

    Object getProxy() {
        Object o = Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("目标方法调用前。。。。");
                    Object obj = method.invoke(object, args); // 按理说，应该会被调用吧。但是并没有
                    System.out.println("目标方法被调用后。。。。");
                    return obj;
                });
        return o;
    }
}
