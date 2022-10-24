package com.eor;


import org.junit.jupiter.api.Test;

class Singleton {
    static Singleton instance = null;

    public Singleton getInstance() {
        if (instance != null) {
            synchronized (Singleton.class) {
                if (instance != null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}