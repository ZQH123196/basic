package com.eor.simple.readWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheConcurrent<K, V> {
    final Map<K, V> cache = new HashMap<K, V>();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    final Lock rl = rwl.readLock();
    final Lock wl = rwl.writeLock();

    V get(K key) {
        // 已经存在缓存中，直接返回
        if (cache.get(key) != null) {
            return cache.get(key);
        }

        V res = null;
        rl.lock();
        try {
            // 多线程情况下，可能有多个线程同时获取了读锁
            // 因此这里要再加一层检查，避免多个线程同时等待锁，并在获得后反复的设置值
            if (cache.get(key) == null) {
                cache.put(key, getByDb(key));
            }
        } finally {
            rl.unlock();
        }
        return res;
    }

    V put(K key, V value) {
        wl.lock();
        try {
            return putByDb(key, value);
        } finally {
            wl.unlock();
        }
    }

    V getByDb(K key) {
        return null;
    }

    V putByDb(K key, V value) {
        return value;
    }
}
