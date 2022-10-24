package com.eor.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * 堵塞队列其实是针对特定场景优化的工具类：
 * 当阻塞队列是空时，从队列中获取元素的操作将会被阻塞。
 * 当阻塞队列是满时，从队列中添加元素的操作将会被阻塞。
 * 是我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，这些 BlockingQueue 都包办了。
 *
 * 记住永远使用有界的，无界容易导致 OOM;
 *
 * 月下七兄贵：
 * ArrayBlockingQueue ：一个由数组结构组成的有界阻塞队列。
 * LinkedBlockingQueue：一个由链表结构组成的有界阻塞队列。
 * PriorityBlockingQueue ：一个支持优先级排序的无界阻塞队列。
 * DelayQueue：一个使用优先级队列实现的无界阻塞队列。
 * SynchronousQueue：一个不存储元素的阻塞队列。
 * LinkedTransferQueue：一个由链表结构组成的无界阻塞队列。
 * LinkedBlockingDeque：一个由链表结构组成的双向阻塞队列。
 *
 */
public class Test {

    public static void main(String[] args) {
    }
}
