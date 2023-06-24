package com.example.java.sm.framework;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class BasicBean {

    /**
     * key：redis 或其他存储区域的 key，由前端传入，一般前端可用 用户名+登录时间 来传入
     * value：sm4 的密钥
     *
     * todo 基本类型不能直接注入，因为额米有 beandefinition 等实现
     *
     * @return
     */
//    @Bean("sm4KeyMap")
//    public Map<String, String> sm4KeyMap() {
//        return new HashMap<>();
//    }
}
