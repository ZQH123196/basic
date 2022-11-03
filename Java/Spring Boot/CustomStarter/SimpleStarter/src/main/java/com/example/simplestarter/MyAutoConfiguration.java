package com.eor.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(SpringApplication.class) // 这个类肯定存在，所以当然会加载，如果是做一个连接池的自动配置，那这里就可以写 datasource
public class MyAutoConfiguration {
    static {
        System.out.println(MyAutoConfiguration.class.getName() + " init......");
    }

    @Bean
    public SimpleBean simpleBean() {
        return new SimpleBean();
    }
}
