package com.example.backendjava.config;

import com.example.backendjava.filter.EncryptFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.Filter;
import java.util.Collections;

@Configuration
@Slf4j
public class WebConfig {


    @Bean
    FilterRegistrationBean<EncryptFilter> registrationBean() {
        FilterRegistrationBean<EncryptFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.setFilter(new EncryptFilter());
        filterRegistrationBean.setUrlPatterns(Collections.singleton("/*"));
        return filterRegistrationBean;
    }
}
