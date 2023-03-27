package com.eor.simple.readWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SimpleReadWrite<K, V> {
    final Map<K, V> cache = new HashMap<K, V>();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    final Lock rl = rwl.readLock();
    final Lock wl = rwl.writeLock();

    V get(K key) {
        V res = null;
        rl.lock();
        try {
            res = cache.get(key);
        } finally {
            rl.unlock();
        }
        return res;
    }

    V put(K key, V value) {
        wl.lock();
        try {
            return cache.put(key, value);
        } finally {
            wl.unlock();
        }
    }

}
