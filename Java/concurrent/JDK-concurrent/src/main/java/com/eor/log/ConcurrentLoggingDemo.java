package com.eor.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 假设有一个需求：要求并发地处理一批任务，每个任务需要打印一些日志，日志中需要包含任务的编号和处理结果。要求每个线程的日志分别输出到自己的控制台，而不是聚合到一起。
 *
 * 在这个案例中，我们创建了一个固定大小的线程池，同时创建了 10 个任务，每个任务需要打印一些日志。在任务中，我们使用 MDC 设置任务编号，并在日志中输出任务编号和处理结果。
 *
 * 在 logback.xml 中，我们配置了 SiftingAppender，使用 taskId 作为 key，将每个任务的日志输出到不同的控制台中。这样，每个线程的日志就会分别输出到自己的控制台了。
 */
public class ConcurrentLoggingDemo {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConcurrentLoggingDemo.class);

    public static void main(String[] args) throws InterruptedException {
        // 创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // 创建任务列表
        List<Task> taskList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            taskList.add(new Task(i));
        }

        // 提交任务
        for (Task task : taskList) {
            executorService.submit(task);
        }

        // 关闭线程池
        executorService.shutdown();

        // 等待所有任务完成
        while (!executorService.isTerminated()) {
            Thread.sleep(100);
        }
    }

    private static class Task implements Runnable {
        private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);
        private int id;

        public Task(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            MDC.put("taskId", String.valueOf(id));
            LOGGER.info("Task {} started", id);

            // 模拟任务处理
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (id % 2 == 0) {
                LOGGER.info("Task {} succeeded", id);
            } else {
                LOGGER.error("Task {} failed", id);
            }

            MDC.remove("taskId");
        }
    }
}

