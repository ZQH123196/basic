package com.eor.log.threadLog;

/**
 * 多线程中每个线程打印自己的日志，相当于一个线程一个日志文件。
 *
 * 对于 Logback 委托给它的日志事件，SiftingAppender 会对日志事件做一些区分，然后不同的事件 SiftingAppender 会委托不同的 appender 去完成真正的写操作。
 */
public class TestSiftingAppender {
}
