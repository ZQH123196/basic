package org.eor.designPattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyLogAroundProxyJava <T> implements InvocationHandler {

    private T target;

    public void MyLogAroundProxy(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        doSomethingBefore();
        Object res = method.invoke(this.target, args);
        doSomethingAfter();
        return res;
    }

    // 根据思想的不同，
    // 一种写法是允许使用从外部传入的 被代理对象
    // 一种写法是不允许使用外部传入的 被代理对象
    public static <T> T getProxyInstance(T target) {
        Object o = Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new MyLogAroundProxy(target));
        return (T) o;
    }

    public void doSomethingBefore() {
        System.out.println("doSomethingBefore");
    }

    public void doSomethingAfter() {
        System.out.println("doSomethingAfter");
    }
}