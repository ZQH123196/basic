package com.example.sbsourcelearning;

import com.example.sbsourcelearning.initialize.SecondInitializer;
import com.example.sbsourcelearning.listener.SecondListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Properties;

@SpringBootApplication
@MapperScan("com.example.sbsourcelearning.mapper")
public class SbSourceLearningApplication {

    public static void main(String[] args) {
//        SpringApplication.run(SbSourceLearningApplication.class, args);
        SpringApplication application = new SpringApplication(SbSourceLearningApplication.class);
//        application.setApplicationStartup(new BufferingApplicationStartup(2048));

        application.setDefaultProperties(new Properties(){{ put("myProp1", "8999"); }});
        application.addInitializers(new SecondInitializer());
        application.addListeners(new SecondListener());
        ConfigurableApplicationContext context = application.run(args);

        String myProp1 = context.getEnvironment().getProperty("myProp1");
        System.out.println("myProp1 = " + myProp1);
        System.out.println("获取 maven 属性：" + context.getEnvironment().getProperty("mysetting.maven.buildStamp"));
    }
}




