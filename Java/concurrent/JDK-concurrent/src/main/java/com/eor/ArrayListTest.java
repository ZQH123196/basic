package com.eor;

import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayListTest {


    /**
     * 这里 100 次测试都会通过
     * 测试 Collections.synchronizedCollection 所返回的线程安全包装类
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

        Assertions.assertEquals(syncCollection.size(), MAX_NUM*2);
    }

    /**
     * 100 次会出错多次
     * 不使用 Collections.synchronizedCollection 包装，
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

        Assertions.assertEquals(syncCollection.size(), MAX_NUM*2);
    }


    /**
     * c100k = 100*1000
     * c10m = 1000w = 1000*10000 = 10000*1000
     *
     * 这里是往一个 synchronized 修饰的 list 中多线程添加，意义为零，理论上只是多了线程上下文切换的损耗，可能比单线程还慢
     * @throws InterruptedException
     */
    @RepeatedTest(10)
    public void testSyncList() throws InterruptedException {
        Faker faker = new Faker();
        int MAX_NUM = 100 * 1000;
        int ThreadCnt = 8;
        List<String> list = Collections.synchronizedList(new ArrayList<>(MAX_NUM));

        ExecutorService executorService = new ThreadPoolExecutor(ThreadCnt, ThreadCnt,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(MAX_NUM));
        // ThreadCnt 个线程，每个线程都添加 MAX_NUM 数量
        for (int i = 0; i < ThreadCnt; i++) {
            executorService.submit(() -> {
                for (int j = 0; j < MAX_NUM; j++) {
                    list.add(faker.name().username());
                }
            });
        }
        executorService.awaitTermination(5, TimeUnit.SECONDS);
        Assertions.assertEquals(MAX_NUM * ThreadCnt, list.size());
    }

    /**
     * 使用局部变量，返回后再合并，这样就没有 synchronized。速度提升了接近十倍
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @RepeatedTest(1)
    public void testAsyncCollect() throws InterruptedException, ExecutionException {
        Faker faker = new Faker();
        int MAX_NUM = 100 * 1000;
        int ThreadCnt = 8;

        ExecutorService executorService = new ThreadPoolExecutor(ThreadCnt, ThreadCnt,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(MAX_NUM));
        ExecutorCompletionService<List<String>> completionService = new ExecutorCompletionService<>(executorService);
        // ThreadCnt 个线程，每个线程都添加 MAX_NUM 数量
        for (int i = 0; i < ThreadCnt; i++) {
            completionService.submit(() -> {
                List<String> _list = Collections.synchronizedList(new ArrayList<>(MAX_NUM));
                for (int j = 0; j < MAX_NUM; j++) {
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

        Assertions.assertEquals(MAX_NUM * ThreadCnt, list.size());
    }
}
