package com.eor.simple;

import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.LongAdder;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ArrayListTest {


    /**
     * 这里 100 次测试都会通过
     * 测试 Collections.synchronizedCollection 所返回的线程安全包装类
     *
     * @throws InterruptedException
     */
    @RepeatedTest(100)
    public void testAsync() throws InterruptedException {
        Collection<Integer> syncCollection = Collections.synchronizedCollection(new ArrayList<>());
        int MAX_NUM = 100 * 10000;
        Runnable listOperations = () -> {
            List<Integer> _collect = Stream.iterate(0, (n) -> n + 1).limit(MAX_NUM).collect(Collectors.toList());
            syncCollection.addAll(_collect);
        };

        Thread thread1 = new Thread(listOperations);
        Thread thread2 = new Thread(listOperations);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Assertions.assertEquals(syncCollection.size(), MAX_NUM * 2);
    }

    /**
     * 100 次会出错多次
     * 不使用 Collections.synchronizedCollection 包装，
     *
     * @throws InterruptedException
     */
    @RepeatedTest(100)
    public void testAsyncErrorUse() throws InterruptedException {
        Collection<Integer> syncCollection = new ArrayList<>();
        int MAX_NUM = 100 * 10000;
        Runnable listOperations = () -> {
            List<Integer> _collect = Stream.iterate(0, (n) -> n + 1).limit(MAX_NUM).collect(Collectors.toList());
            syncCollection.addAll(_collect);
        };

        Thread thread1 = new Thread(listOperations);
        Thread thread2 = new Thread(listOperations);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        Assertions.assertEquals(syncCollection.size(), MAX_NUM * 2);
    }


    /**
     * 主要是用于确认其是线程安全的，根据多次生成的数据正确来判断
     * c100k = 100*1000
     * c10m = 1000w = 1000*10000 = 10000*1000
     * <p>
     * 这里是往一个 synchronized 修饰的 list 中多线程添加，意义为零，理论上只是多了线程上下文切换的损耗，可能比单线程还慢
     */
    @RepeatedTest(3)
    public void testSyncList() throws InterruptedException {
        Faker faker = new Faker();
        int MAX_NUM = 1000 * 1000;
        AtomicInteger atomicInteger = new AtomicInteger();
        int ThreadCnt = Runtime.getRuntime().availableProcessors();
        List<String> list = Collections.synchronizedList(new ArrayList<>(MAX_NUM));

        ExecutorService executorService = new ThreadPoolExecutor(ThreadCnt, ThreadCnt,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(MAX_NUM));
        for (int i = 0; i < ThreadCnt; i++) {
            executorService.submit(() -> {
                for (; atomicInteger.getAndIncrement() < MAX_NUM; ) {
                    list.add(faker.name().username());
                }
            });
        }
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        Assertions.assertEquals(MAX_NUM, list.size());
    }

    /**
     * 与上面最大的区别就是 list 放置的位置。
     * 在上面的基础上，避免 synchronized 来提升性能
     * 使用局部变量，返回后再合并，这样就没有 synchronized。
     * 速度提升了接近十倍，相当于空间换时间。
     */
    @RepeatedTest(10)
    public void testAsyncCollect() throws InterruptedException, ExecutionException {
        Faker faker = new Faker();
        int MAX_NUM = 10000 * 1000;
        AtomicInteger atomicInteger = new AtomicInteger();
        int ThreadCnt = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = new ThreadPoolExecutor(ThreadCnt, ThreadCnt,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(MAX_NUM));
        ExecutorCompletionService<List<String>> completionService = new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < ThreadCnt; i++) {
            completionService.submit(() -> {
                List<String> _list = Collections.synchronizedList(new ArrayList<>(MAX_NUM));
                for (; atomicInteger.getAndIncrement() < MAX_NUM; ) {
                    _list.add(faker.name().username());
                }
                return _list;
            });
        }
        List<String> list = Collections.synchronizedList(new ArrayList<>(MAX_NUM));
        for (int i = 0; i < ThreadCnt; i++) {
            List<String> _list = completionService.take().get();
            list.addAll(_list);
        }
        Assertions.assertEquals(MAX_NUM, list.size());
    }


    /**
     * 性能跟上面一样，在线程数低的情况下移除 atomic，或者说是移除 volatile 的意义不大
     * 将上面的程序改写成提前根据线程进行分割的数据量，主要是试一下 l1、l2 缓存会不会快一些，但是很难确认这一点
     * 只能说 volatile 对性能的影响倒是不大
     * 第一次 大约 2.5s
     * 后面的 aot 优化后有 1.8s 左右
     */
    @RepeatedTest(1)
    public void testAsyncCollect10000w() throws InterruptedException, ExecutionException {
        Faker faker = new Faker();
        int MAX_NUM = 10000 * 1000;
        int ThreadCnt = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = new ThreadPoolExecutor(ThreadCnt, ThreadCnt,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(MAX_NUM));
        ExecutorCompletionService<List<String>> completionService = new ExecutorCompletionService<>(executorService);

        ArrayList<Integer> perThreadHandleNum = new ArrayList<>(ThreadCnt);
        int _n = MAX_NUM / ThreadCnt;
        for (int i = 0; i < ThreadCnt; i++) {
            perThreadHandleNum.add(_n);
        }
        // 因为可能除不完，所以最后在多加一个线程去算
        if (!(MAX_NUM % ThreadCnt == 0)) {
            perThreadHandleNum.add(MAX_NUM % ThreadCnt);
        }

        for (Integer num : perThreadHandleNum) {
            completionService.submit(() -> {
                List<String> _list = Collections.synchronizedList(new ArrayList<>(MAX_NUM % ThreadCnt));
                for (int i = 0; i < num; i++) {
                    _list.add(faker.name().username());
                }
                return _list;
            });
        }

        List<String> list = Collections.synchronizedList(new ArrayList<>(MAX_NUM));
        for (int i = 0; i < perThreadHandleNum.size(); i++) {
            List<String> _list = completionService.take().get();
            list.addAll(_list);
        }
        System.out.println("ThreadCnt=" + ThreadCnt);
        System.out.println("list.size()=" + list.size());
        Assertions.assertEquals(MAX_NUM, list.size());
    }

}
