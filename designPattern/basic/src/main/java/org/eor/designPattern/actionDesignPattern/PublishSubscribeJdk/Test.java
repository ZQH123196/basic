package org.eor.designPattern.actionDesignPattern.PublishSubscribeJdk;

import java.util.function.Consumer;

public class Test {
    public static void main(String[] args) {
        Event event = new Event("事件");

        Listener listener1 = new Listener((source) -> {
            System.out.println("listener 1：" + source);
        });

        Listener listener2 = new Listener((source) -> {
            System.out.println("listener 2：" + source);
        });

        event.registerListener(listener1);
        event.registerListener(listener2);

        event.notification();
    }
}
