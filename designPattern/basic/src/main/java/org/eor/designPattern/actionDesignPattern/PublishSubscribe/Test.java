package org.eor.designPattern.actionDesignPattern.PublishSubscribe;


import java.util.ArrayList;
import java.util.List;

public class Basic {
    public static void main(String[] args) {
        CreateSubject createSubject = new CreateSubject();
        createSubject.registerObserver(new ConcreateObeserverA());
        createSubject.registerObserver(new ConcreateObeserverB());
        createSubject.registerObserver(new ConcreateObeserverC());
        createSubject.registerObserver(new ConcreateObeserverA());
        createSubject.registerObserver(new ConcreateObeserverA());

        createSubject.notificationObserver(new Message("new Message!"));
    }
}















