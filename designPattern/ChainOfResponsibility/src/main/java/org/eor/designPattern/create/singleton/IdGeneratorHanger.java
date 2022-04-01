package org.eor.designPattern.create.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 饿汉式，无懒加载
 */
public class IdGenerator {
    private AtomicLong id = new AtomicLong();
    private static final IdGenerator instance = new IdGenerator();
    // 私有构造，阻止 new
    private IdGenerator() {}

    public IdGenerator getInstance() {
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
