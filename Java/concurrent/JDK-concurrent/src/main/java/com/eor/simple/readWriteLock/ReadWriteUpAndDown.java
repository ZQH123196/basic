package com.eor.simple.readWriteLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 获取写锁的前提是读锁和写锁均未被占用
 * 获取读锁的前提是没有其他线程占用写锁
 * 申请写锁时不中断其他线程申请读锁
 * 公平锁如果过有写申请，能禁止读锁
 *
 * 可以从写锁降级到读锁，因为写的权限本身就包含了读，模型就是运行写时不可读，除非是正在写的线程。
 * 但是不可以从读锁升级到写锁，这是越权的行为。不过 jdk 的实现中并没有禁止这个操作，所以很容易出现死锁。
 * 而且读锁是并发读的，一旦允许的话就可能出现多个线程中各自升级到写锁的情况，那读写锁模型的意义就不存在了。
 * 这段程序只是为了证明可以从写锁降级到读锁
 * 注意写锁中使用读锁时，读锁生存期是独立的。
 */
public class ReadWriteUpAndDown<K, V> {
    Object data;
    volatile boolean cacheValid = false;
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    final Lock rl = rwl.readLock();
    final Lock wl = rwl.writeLock();

    void processCachedData(K key, V value) {
        // 获取读锁
        rl.lock();
        if (!cacheValid) {
            // 释放读锁，因为不允许读锁的升级
            rl.unlock();
            // 获取写锁
            wl.lock();
            try {
                // 再次检查状态
                if (!cacheValid) {
                    data = getByDb(key);
                    cacheValid = true;
                }
                // 释放写锁前，降级为读锁
                // 降级是可以的
                rl.lock();
            } finally {
                // 释放写锁
                wl.unlock();
            }
        }
        // 此处仍然持有读锁
        try {
            use(data);
        } finally {
            rl.unlock();
        }
    }

    V getByDb(K key) {
        return null;
    }

    void use(Object data) {
    }
}
