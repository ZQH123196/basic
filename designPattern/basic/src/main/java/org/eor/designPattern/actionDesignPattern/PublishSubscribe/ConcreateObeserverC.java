package org.eor.designPattern.actionDesignPattern.PublishSubscribe;

class ConcreateObeserverC implements Observer {
    @Override
    public void update(Message msg) {
        System.out.println("ConcreateObeserverC! receive msg = " + msg.getInfo());
    }
}
