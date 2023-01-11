package org.eor.designPattern.actionDesignPattern.PublishSubscribe;


public class Test {
    public static void main(String[] args) {
        Subject subject = new CreateSubject();
        subject.registerObserver(new ConcreateObeserverA());
        subject.registerObserver(new ConcreateObeserverB());
        subject.registerObserver(new ConcreateObeserverC());
        subject.registerObserver(new ConcreateObeserverA());
        subject.registerObserver(new ConcreateObeserverA());

        subject.notificationObserver(new Message("new Message!"));
    }
}















