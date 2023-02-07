package com.example.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Value("${username}")
    private String username;

    public String test() {
        return username;
    }
}
