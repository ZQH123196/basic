package org.eor.designPattern.create.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Java 规范字规定，每个枚举类型及其定义的枚举变量在 JVM 中都是唯一的，因此在枚举类型的序列化和反序列化上，Java 做了特殊的规定。
 * 在序列化的时候 Java 仅仅是将枚举对象的 name 属性输到结果中，反序列化的时候则是通过 java.lang.Enum 的 valueOf() 方法来根据名字查找枚举对象。
 * 也就是说，序列化的时候只将枚举的名称字符串输出，反序列化的时候再通过这个名称，查找当前系统内对应的枚举类型，因此反序列化后的实例也会和之前被序列化的对象实例相同。
 */
public enum IdGeneratorEnum {
    instance;
    private static AtomicLong id = new AtomicLong(0);

    public static long getId() {
        return id.incrementAndGet();
    }
}
