package org.eor.designPattern.proxy;

public class Run {
    public static void main(String[] args) {
        OriginJava origin = new OriginJavaImpl();
        OriginJava proxy = MyLogAroundProxyJava.getProxyInstance(origin);
        proxy.doSomething();
    }
}
