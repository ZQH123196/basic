package com.eor.stampedLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.StampedLock;

/**
 * 这个就是乐观锁，主要查看源码内的使用案例
 * 乐观锁支持三种模式，写锁、悲观读锁、乐观读锁。
 * 写锁、悲观读锁就是 readwritelock 那种读写完全互斥的情况。
 * 因此跟 readwirtelock 的差别是乐观读锁是无锁设计，而且允许读时写，是乐观的认为写对此处读没有影响。
 */
public class SimpleStampedLock {
    static final StampedLock stampedLock = new StampedLock();

    public static void main(String[] args) {
        // 获取悲观读锁
        long readStamp = stampedLock.readLock();
        try {
            // read something
        } finally {
            stampedLock.unlockRead(readStamp);
        }

        //
        long writeStamp = stampedLock.writeLock();
        try {
            // write something
        } finally {
            stampedLock.unlockWrite(writeStamp);
        }

        long optimisticStamp = stampedLock.tryOptimisticRead();
        try {
            // 先读共享数据，read something
            // 乐观读之后数据可能被修改，所以这里一定要在验证
            if (stampedLock.validate(optimisticStamp)) {
                // read something
            }
        } finally {
            stampedLock.unlockRead(optimisticStamp);
        }


        // 执行乐观读操作期间，如果存在写操作，那么改为为悲观锁
        long readSmartStamp = stampedLock.tryOptimisticRead();
        try {
            // 先读共享数据，read something
            // 验证不通过的话升级为悲观锁
            if (!stampedLock.validate(readSmartStamp)) {
                // 升级为悲观读锁
                Long _stamp = stampedLock.readLock();
                if (_stamp != 0L) {
                    readSmartStamp = _stamp;
                }
                // read something
            }
        } finally {
            // 只需要释放一次
            stampedLock.unlockRead(readSmartStamp);
        }


        // 读锁升级写锁，乐观读、悲观读都可以升级
        long readLockUpStamp = stampedLock.tryOptimisticRead();
        try {
            long writeStampUpdated = stampedLock.tryConvertToWriteLock(readLockUpStamp);
            try {
                // write something
            } finally {
                stampedLock.unlockWrite(writeStampUpdated);
            }
        } finally {
            stampedLock.unlockRead(readLockUpStamp);
        }


    }

}
