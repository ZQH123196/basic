package com.example.sbsourcelearning.listener;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;

@Order(3)
public class ThirdListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) { System.out.println(this.getClass().getName());
    }
}
