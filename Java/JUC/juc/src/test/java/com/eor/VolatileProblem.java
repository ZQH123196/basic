package com.eor;

/**
 * 只在 JDK1.5 之前存在这个问题
 */
public class VolatileProblem {
    static int x = 0;
    volatile static boolean v = false;
    public void writer() {
        x = 42;
        v = true;
    }
    public void reader() {
        if (v) {
            System.out.println(x);
        }
    }
}
