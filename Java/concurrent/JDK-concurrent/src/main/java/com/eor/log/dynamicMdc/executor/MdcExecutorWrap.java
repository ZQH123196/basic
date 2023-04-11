package com.eor.log.dynamicMdc.executor;

import java.util.concurrent.*;

/**
 * https://blog.csdn.net/uuqaz/article/details/124404806
 */
public class MdcExecutorWrap {

    ExecutorService executorService = Executors.newFixedThreadPool(8);


    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    public <T> Future<T> submit(Callable<T> task) {
        return executorService.<T>submit(task);
    }


    public void shutdown() {
        executorService.shutdown();
    }
}

