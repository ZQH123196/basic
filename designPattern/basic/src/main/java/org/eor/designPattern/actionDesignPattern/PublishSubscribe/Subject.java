package org.eor.designPattern.actionDesignPattern.PublishSubscribe;

interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notificationObserver(Message message);
}