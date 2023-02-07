package com.example.nacosspringcloud.autorefresh;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
//@ConfigurationProperties(prefix = "autorefresh.conf")
public class AutoRefreshConf {

    @Value("${autorefresh.conf.username}")
    private String username;

    @Value("${autorefresh.conf.password}")
    private String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
