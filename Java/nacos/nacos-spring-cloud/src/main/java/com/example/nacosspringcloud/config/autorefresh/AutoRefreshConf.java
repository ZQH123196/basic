package com.example.nacosspringcloud.config.autorefresh;

import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
//@NacosConfigurationProperties(prefix = "autorefresh.conf", dataId = "test")
@ConfigurationProperties(prefix = "autorefresh.conf")
public class AutoRefreshConf {

//    @Value("${autorefresh.conf.username}")
    private String username;

//    @Value("${autorefresh.conf.password}")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
