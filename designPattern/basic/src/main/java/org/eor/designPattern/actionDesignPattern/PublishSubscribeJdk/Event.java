package org.eor.designPattern.actionDesignPattern.PublishSubscribeJdk;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

public class Event extends EventObject {

    private List<Listener> listenerList = new ArrayList<>();

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public Event(Object source) {
        super(source);
    }

    public void registerListener(Listener listener) {
        listenerList.add(listener);
    }

    public void notification() {
        for (Listener listener : listenerList) {
            listener.doSomething(source);
        }
    }
}
