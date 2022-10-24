package com.eor.spring;

import org.junit.jupiter.api.Test;

class TestThread {

    static int count = 0;

    public void add10K() {
        int max = 10*1000;
        int before = 0;
        for (int i = 0; i < max; i++) {
            count += 1;
        }
    }



    @Test
    public void testThread() throws InterruptedException {
        final TestThread testThread = new TestThread();

        Thread th1 = new Thread(() -> { testThread.add10K(); });
        Thread th2 = new Thread(() -> { testThread.add10K(); });

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println(count);
    }


}