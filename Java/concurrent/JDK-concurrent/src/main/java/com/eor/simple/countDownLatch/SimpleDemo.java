package com.eor.simple.countDownLatch;


import java.util.concurrent.CountDownLatch;

/**
 * await 具有阻塞作用，类比 join
 */
public class SimpleDemo {

    public static void main(String[] args) {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);

        for(int i=0; i<2; i++){
            Thread thread = new Thread(new Player(begin,end));
            thread.start(); // 子线程立即运行了 run，但是运行到 began.await() 时会被阻塞
        }

        try{
            System.out.println("the race begin");
            begin.countDown(); // 此时 player 才从 began.await() 出被唤醒执行
            end.await(); // 一直阻塞直到所有的 player 都完成
            System.out.println("the race end");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}


/**
 * 选手
 */
class Player implements Runnable{
    private CountDownLatch begin;
    private CountDownLatch end;
    Player(CountDownLatch begin,CountDownLatch end){
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            begin.await();
            System.out.println(Thread.currentThread().getName() + " arrived !");;
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}