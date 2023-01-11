package org.eor.designPattern.actionDesignPattern.PublishSubscribe;

import java.util.ArrayList;
import java.util.List;

class CreateSubject implements Subject {

    List<Observer> observerList = new ArrayList<>();

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void notificationObserver(Message msg) {
        for (Observer observer :
                observerList) {
            observer.update(msg);
        }
    }
}

