package com.example.nacosspringboot.service;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    // spring boot 不支持 @Value 自动刷新，不过 spring cloud 是支持的
    @Value("${my.username}")
    private String username;

    // spring boot 只支持 @NacosValue 并且设置 autoRefreshed = true 时刷新
    @NacosValue(value = "${my.password}", autoRefreshed = true)
    private String password;

    public String test() {
        return username+":"+password;
    }
}
