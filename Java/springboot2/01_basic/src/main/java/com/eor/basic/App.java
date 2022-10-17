package com.eor.basic;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
//        SpringApplication app = new SpringApplication(App.class);
        SpringApplication app = new MyApp(App.class);
        app.setApplicationStartup(new BufferingApplicationStartup(2048));
        ConfigurableApplicationContext context = app.run();

        Duration timeTakenToStartup = Duration.ofNanos(System.nanoTime() - startTime);
        System.out.println("time: " +timeTakenToStartup.toMillis());
    }

}

class MyApp extends SpringApplication {

    public MyApp(Class<App> appClass) {
        super(appClass);
    }

    /**
     * 模板模式 重写 afterRefresh
     * @param context
     * @param args
     */
    @Override
    protected void afterRefresh(ConfigurableApplicationContext context, ApplicationArguments args) {
        super.afterRefresh(context, args);
        System.out.println("rewrite afterRefresh!");
    }
}