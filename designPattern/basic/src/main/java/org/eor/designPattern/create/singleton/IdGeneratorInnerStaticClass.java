package org.eor.designPattern.create.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 利用了 jvm 对于静态内部类的初始化规则，jvm 不会立即载入静态内部类，所以静态内部类的内容不会立即运行，懒加载。
 * SingletonHolder 是一个静态内部类，当外部类 IdGenerator 被加载的时候，并不会创建 SingletonHolder 实例对象。
 * 只有当调用 getInstance() 方法时，SingletonHolder 才会被加载，这个时候才会创建 instance。instance 的唯一性、创建过程的线程安全性，都由 JVM 来保证。
 * 所以，这种实现方法既保证了线程安全，又能做到延迟加载。
 */
public class IdGeneratorInnerStaticClass {
    private static AtomicLong id = new AtomicLong(0);
    private static class SingletonHolder {
        private static final IdGeneratorInnerStaticClass instance = new IdGeneratorInnerStaticClass();
    }

    public IdGeneratorInnerStaticClass getInstance() {
        return SingletonHolder.instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
