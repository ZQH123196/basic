package org.eor.designPattern.create.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 饿汉式，无懒加载
 */
public class IdGeneratorHanger {
    private static AtomicLong id = new AtomicLong(0);
    private static final IdGeneratorHanger instance = new IdGeneratorHanger();
    // 私有构造，阻止 new
    private IdGeneratorHanger() {}

    public IdGeneratorHanger getInstance() {
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
