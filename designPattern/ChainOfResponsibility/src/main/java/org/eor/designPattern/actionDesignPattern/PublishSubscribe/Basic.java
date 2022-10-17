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



interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notificationObserver(Message message);
}

interface Observer {
    void update(Message msg);
}

class Message {
    private String info;

    Message(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}

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


class ConcreateObeserverA implements Observer {
    @Override
    public void update(Message msg) {
        System.out.println("ConcreateObeserverA! receive msg = " + msg.getInfo());
    }
}

class ConcreateObeserverB implements Observer {
    @Override
    public void update(Message msg) {
        System.out.println("ConcreateObeserverB! receive msg = " + msg.getInfo());
    }
}

class ConcreateObeserverC implements Observer {
    @Override
    public void update(Message msg) {
        System.out.println("ConcreateObeserverC! receive msg = " + msg.getInfo());
    }
}