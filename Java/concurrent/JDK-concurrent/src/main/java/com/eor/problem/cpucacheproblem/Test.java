package com.eor.problem.cpucacheproblem;

/**
 * 值可能是 10k 到 20k 中的任何数
 * 因为缓存的可见性问题
 * 假设线程 A 和线程 B 同时开始执行，那么第一次都会将 count=0 读到各自的 CPU 缓存里，
 * A B 线程执行完 count+=1 之后，各自 CPU 缓存里的值都是 1，
 * 同时写入内存后，我们会发现内存中的值会是 1，而不是我们期望的 2。
 * 之后由于各自的 CPU 缓存里都有了 count 的值，两个线程都是基于 CPU 缓存里的 count 值来计算，
 * 所以导致最终 count 的值都是小于 20000 的。这就是缓存的可见性问题。
 */
public class Test {
    private static long count = 0;

    public static void main(String[] args) throws InterruptedException {
        System.out.printf(String.valueOf(calc()));
    }

    private void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count += 1;
        }
    }

    public static long calc() throws InterruptedException {
        final Test test = new Test();
        // 创建两个线程，执行add()操作
        Thread th1 = new Thread(() -> {
            test.add10K();
        });
        Thread th2 = new Thread(() -> {
            test.add10K();
        });
        // 启动两个线程
        th1.start();
        th2.start();
        // 等待两个线程执行结束
        th1.join();
        th2.join();
        return count;
    }


}
