package com.example.sbsourcelearning.event;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 在一个特定的时机添加属性：在 banner 打印之前设置 banner 中需要用到的属性，并且属性来自 maven
 */
@Component
public class MyPrepareContextEnv implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        System.out.println(this.getClass().getName());
        ConfigurableEnvironment env = event.getEnvironment();
        String buildStamp = env.getProperty("mysetting.maven.buildStamp");
        System.out.println("获取 maven 属性：" + buildStamp);
        event.getSpringApplication().setDefaultProperties(new Properties() {{
            put("buildStamp", buildStamp);
        }});


        env.validateRequiredProperties();
    }


//    @Override
//    public void onApplicationEvent(ApplicationContextInitializedEvent event) {
//        String buildStamp = event.getApplicationContext().getEnvironment().getProperty("mysetting.maven.buildStamp");
//        System.out.println("into PrepareContextEnv onApplicationEvent!");
//        System.out.println("获取 maven 属性：" + buildStamp);
//    }

}




