package org.eor.designPattern.create.singleton;

import java.util.concurrent.atomic.AtomicLong;

public class IdGeneratorDoubleCheck {
    private static AtomicLong id = new AtomicLong(0);
    private static IdGeneratorDoubleCheck instance = null;
    private IdGeneratorDoubleCheck() {}

    // 双重检查也是类锁，但是只有第一次初始化会进入锁，相比懒汉式要轻一些
    public static IdGeneratorDoubleCheck getInstance() {
        if (instance == null) {
            synchronized (IdGeneratorDoubleCheck.class) {
                if (instance == null) {
                    instance = new IdGeneratorDoubleCheck();
                }
            }
        }
        return instance;
    }
}
