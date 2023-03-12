package org.example;

import net.datafaker.Faker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * https://www.baeldung.com/java-datafaker
 * <p>
 * 第一行 table name
 * 第二行起数据，逗号分割的 csv
 */
public class TestDataFaker {


    @RepeatedTest(1)
    public void test1() {
        Faker faker = new Faker();

        String name = faker.name().fullName(); // Miss Samanta Schmidt
        String firstName = faker.name().firstName(); // Emory
        String lastName = faker.name().lastName(); // Barton

        String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449

        System.out.println(name);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(streetAddress);
        System.out.println("--------------------");
    }


    @RepeatedTest(1)
    public void testSync() {
        Faker faker = new Faker();
        int MAX_NUM = 10000 * 10000;
        List<String> list = Collections.synchronizedList(new ArrayList<>(MAX_NUM));

        for (int i = 0; i < MAX_NUM; i++) {
            list.add(faker.name().username());
        }
        System.out.println(list.size());
        System.out.println("--------------------");
    }


    @RepeatedTest(10)
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

        List<String> list = Collections.synchronizedList(new ArrayList<>(MAX_NUM*ThreadCnt));
        for (int i = 0; i < ThreadCnt; i++) {
            List<String> _list = completionService.take().get();
            list.addAll(_list);
        }

        Assertions.assertEquals(MAX_NUM * ThreadCnt, list.size());
    }


}