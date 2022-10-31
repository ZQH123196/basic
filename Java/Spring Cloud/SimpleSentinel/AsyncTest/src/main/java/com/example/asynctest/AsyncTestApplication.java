package com.example.asynctest;

import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;

@SpringBootApplication
public class AsyncTestApplication implements ApplicationRunner, ApplicationContextAware {

    public static void main(String[] args) {

        new SpringApplicationBuilder()
                .sources(AsyncTestApplication.class)
                .web(WebApplicationType.NONE)
                .build()
                .run(args);

    }


    ConfigurableApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (applicationContext instanceof ConfigurableApplicationContext) {
            appContext = (ConfigurableApplicationContext) applicationContext;
        }
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {

        new Thread(() -> {
            try {
                asyncTest4Hello();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).start();


        // 跑完就关闭
        appContext.close();
    }

    private void asyncTest4Hello() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        CompletionService completionService = new ExecutorCompletionService<>(executorService);
        String url = "http://localhost:8080/EchoServer/hello";
        for (int i = 0; i < 5; i++) {
            completionService.submit(() -> {
                RestTemplate restTemplate = appContext.getBean(RestTemplate.class);
                String res = restTemplate.getForObject(url, String.class);
                return res;
            });
        }

        // 由于是无序输出，因此拿到结果的后面才响应，最快执行完成的先输出
        for (int i = 0; i < 5; i++) {
            System.out.println(completionService.take().get());
        }
    }


}
