package org.eor.designPattern.actionDesignPattern.PublishSubscribe;

class ConcreateObeserverB implements Observer {
    @Override
    public void update(Message msg) {
        System.out.println("ConcreateObeserverB! receive msg = " + msg.getInfo());
    }
}