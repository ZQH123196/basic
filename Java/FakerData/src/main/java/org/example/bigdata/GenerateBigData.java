package org.example.bigdata;

import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class GenerateBigData {


    private final int num1000w = 10*1000;
    // 这个即使多线程下每次都创建，也不会提高性能，本身优化的就可以了
    Faker faker = new Faker();


    /**
     * 第一次 大约 2.5s
     * 后面的 aot 优化后有 1.8s 左右
     */
    @RepeatedTest(10)
    public void test1000w() throws InterruptedException, ExecutionException {
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
