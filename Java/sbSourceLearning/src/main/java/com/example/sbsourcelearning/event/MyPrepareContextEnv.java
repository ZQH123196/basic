package com.example.sbsourcelearning.event;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

@Component
public class PrepareContextEnv implements ApplicationListener<ApplicationStartingEvent> {
//    @Override
//    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
//        String buildStamp = event.getEnvironment().getProperty("mysetting.maven.buildStamp");
//        System.out.println("into PrepareContextEnv onApplicationEvent!");
//        System.out.println("获取 maven 属性：" + buildStamp);
//    }


//    @Override
//    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
//        String buildStamp = event.getApplicationContext().getEnvironment().getProperty("mysetting.maven.buildStamp");
//        System.out.println("into PrepareContextEnv onApplicationEvent!");
//        System.out.println("获取 maven 属性：" + buildStamp);
//    }

    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) { System.out.println(this.getClass().getName());
    }
}




