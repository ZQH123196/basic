package org.example.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

/**
 * # Warmup: 5 iterations, 10 s each 预热 5 轮，每轮 10s，即 10s 内持续运行方法来让 jdk 标记热点代码
 * # Measurement: 5 iterations, 10 s each 正式测试 5 轮，每轮 10s，即 10s 内持续运行，并统计其测试数据
 * # Timeout: 10 min per iteration 每轮最大超时时间，有些方法可能会运行很久，卡死线程
 * # Threads: 1 thread, will synchronize iterations 一个线程运行，是同步运行的
 * # Benchmark mode: Throughput, ops/time 统计的模式，使用 Throughput（吞吐量） ops/time（每秒运行次数）
 *
 *
 * Result "org.example.jmh.JMHSample_01_HelloWorld.wellHelloThere":
 *   3425856416.811 ±(99.9%) 154024950.322 ops/s [Average]
 *   (min, avg, max) = (3367974551.980, 3425856416.811, 3466742433.660), stdev = 39999790.471
 *   CI (99.9%): [3271831466.489, 3579881367.133] (assumes normal distribution) 置信区间 99.9%，统计学意义上可信
 */
public class JMHSample_01_HelloWorld {

    @Benchmark
    public void wellHelloThere() {
        // this method was intentionally left blank.
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHSample_01_HelloWorld.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
