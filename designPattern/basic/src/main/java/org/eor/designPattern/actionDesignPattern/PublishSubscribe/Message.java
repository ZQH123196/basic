package org.eor.designPattern.actionDesignPattern.PublishSubscribe;

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
