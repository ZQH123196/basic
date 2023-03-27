package com.eor.simple.atomic;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.LongAdder;

/**
 * https://www.bilibili.com/video/BV1KE411K7Ts/?spm_id_from=333.337.search-card.all.click&vd_source=a68b399d6e8e5b548bb0c0266470dbae
 * AtomicLong 是 Doug Lea 在 jdk1.5 版本发布于 java.util.concurrent.atomic 并发包下的类。
 * LongAdder是 Doug Lea 在 java8 中发布的类。
 * 原理都是 volatile 直接写入共享缓存。
 *
 * AtomicLong 在线程数量大的情况下，自旋数过多
 * LongAdder 1.8 解决了这个问题，性能更高
 *
 *
 * AtomicLong的原子性自增操作，是通过 CAS 实现的，简单的说就是乐观锁。
 * 在多线程竞争不激烈的情况下，这样做是合适的。但是如果线程竞争激烈，会造成大量线程在原地打转、不停尝试去修改值，但是老是发现值被修改了，于是继续自旋尝试。这样浪费了大量的CPU资源。
 * 而且，由于AtomicLong持有的成员变量value是volatile关键字修饰的，线程修改了临界资源后，需要刷新到其他线程，也是要费一番功夫的。
 *
 * LongAdder 就是将 AtomicLong 进行了批量优化，不再是一次次的改值，而是一批一批的改值。
 * 其简单的讲就是改变了 自旋 的部分，现在是一种批处理的思想
 * 看 longAdder.add() if ((cs = cells) != null || !casBase(b = base, b + x))
 * 可以看到一个 casBase，这个就是先试一下 cas 能不能行，能就直接赋值，失败了才走 longAdder 批量处理的逻辑
 * 不能，就说明已经有线程改过值了，这时候将自己的值（新旧间的差值）存起来，等到可以变更时，拿新的 base 值来加上自己的差值。
 * 这种可以看到一个是 base 值，一个是当前线程所增加的差值，因此这是一个基于数值运算的实现，在这个前提下可以实现安全的变更和更高的性能。
 *
 * cas 失败，就走批处理，即
 * java.util.concurrent.atomic.Striped64#longAccumulate(long, java.util.function.LongBinaryOperator, boolean, int)
 * Striped64 内部维护了一个 cells 数组，因此就是把 thread:数值 的形式存进去，当然实际不是键值对，而是更类似于 mask 掩码的形式，性能很高可读性很低。
 *
 * 那么存入 待处理 中后，什么时候才开始处理呢？
 *
 *
 * 可以看到，longAdder 通过这种方式避免了 atomicLong 在线程多情况下的 do-while 循环
 */
public class TestLongAddr {



    @Test
    public void longAdder() {
        LongAdder longAdder = new LongAdder();
//        longAdder.add();
    }
}
