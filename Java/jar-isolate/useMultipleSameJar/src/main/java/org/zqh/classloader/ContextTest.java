package org.zqh.classloader;

import java.net.URL;

public class ContextTest {

    public static void main(String[] args) {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassLoader = systemClassLoader.getParent();
        ClassLoader bootstrapClassLoader = extClassLoader.getParent(); // null

        System.out.println(Thread.currentThread().getId() + "-outer:" + Thread.currentThread().getContextClassLoader());

        Thread threadWithDefaultContextClassLoader = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": " + Thread.currentThread().getContextClassLoader());
            }
        };
        threadWithDefaultContextClassLoader.setName("threadWithDefaultContextClassLoader");
        threadWithDefaultContextClassLoader.start();

        Thread threadWithContext = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": " + Thread.currentThread().getContextClassLoader());
            }
        };
        threadWithContext.setName("threadWithContext");
        threadWithContext.setContextClassLoader(extClassLoader);
        threadWithContext.start();

        Thread threadWithCustomsContexClassLoader = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": " + Thread.currentThread().getContextClassLoader());
            }
        };
        threadWithCustomsContexClassLoader.setName("threadWithCustomsContexClassLoader");
        threadWithCustomsContexClassLoader.setContextClassLoader(new MyClassloader(new URL[]{}));
        threadWithCustomsContexClassLoader.start();


    }
}