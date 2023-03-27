package com.eor.simple.blockingQueue.example;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class A {
    private static final BlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(3);

    public static void main(String[] args) {
        arrayBlockingQueue.add("a");
        arrayBlockingQueue.add("b");
        arrayBlockingQueue.add("c");

        arrayBlockingQueue.element();
    }
    public static void doSomething() {
    }
}
