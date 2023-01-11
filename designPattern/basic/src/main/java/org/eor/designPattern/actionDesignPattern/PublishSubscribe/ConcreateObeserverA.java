package org.eor.designPattern.actionDesignPattern.PublishSubscribe;

class ConcreateObeserverA implements Observer {
    @Override
    public void update(Message msg) {
        System.out.println("ConcreateObeserverA! receive msg = " + msg.getInfo());
    }
}
