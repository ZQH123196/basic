package org.eor.designPattern.proxy;

public class OriginJavaImpl implements OriginJava{
    @Override
    public String doSomething() {
        String str = "doSomething";
        System.out.println(str);
        return str;
    }
}
