package org.eor.designPattern.create.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 懒汉式，懒加载
 */
public class IdGeneratorLazy {
    private static AtomicLong id = new AtomicLong(0);
    private static IdGeneratorLazy instance = null;
    private IdGeneratorLazy() {}

    // 要加互斥锁，因为是加在 static 上，是个类锁，太过重量级
    public synchronized static IdGeneratorLazy getInstance() {
        if (instance == null) {
            instance = new IdGeneratorLazy();
        }
        return instance;
    }

    public static long getId() {
        return id.incrementAndGet();
    }
}
