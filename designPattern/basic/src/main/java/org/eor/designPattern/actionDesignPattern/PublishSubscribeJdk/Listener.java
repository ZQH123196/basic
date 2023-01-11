package org.eor.designPattern.actionDesignPattern.PublishSubscribeJdk;

import java.util.EventListener;
import java.util.function.Consumer;

public class Listener implements EventListener {

    private Consumer<Object> fn;

    Listener(Consumer<Object> fn) {
        this.fn = fn;
    }

    public void doSomething(Object source) {
        fn.accept(source);
    }
}
