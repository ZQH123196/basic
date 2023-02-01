package com.example.springsecurity.jwt;


import com.example.springsecurity.jwt.JwtTokenGenerator;
import com.example.springsecurity.jwt.pojo.JwtProperties;
import com.example.springsecurity.jwt.pojo.JwtTokenCacheStorage;
import com.example.springsecurity.jwt.pojo.JwtTokenPair;
import com.example.springsecurity.jwt.pojo.JwtTokenStorage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.security.KeyFactory;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.HashMap;
import java.util.HashSet;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class JwtTest {
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    @WithMockUser(value = "Felordcn", password = "12345")
//    public void contextLoads() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/foo/test")).andExpect(SecurityMockMvcResultMatchers.authenticated());
//    }

    @Test
    public void jwtTest(){
        HashSet<String> roles = new HashSet<>();
        HashMap<String, String> additional = new HashMap<>();
        additional.put("uname","Felordcn");
        JwtProperties jwtProperties = new JwtProperties();
        jwtProperties.setKeyLocation("keytool/rsa/felordcn.jks");
        jwtProperties.setKeyAlias("felordcn");
        jwtProperties.setKeyPass("123456");
        jwtProperties.setIss("felord.cn");
        jwtProperties.setSub("all");
        jwtProperties.setAccessExpDays(30);
        jwtProperties.setRefreshExpDays(90);
        JwtTokenStorage jwtTokenStorage = new JwtTokenCacheStorage();
        JwtTokenGenerator jwtTokenGenerator = new JwtTokenGenerator(jwtTokenStorage, jwtProperties);

        JwtTokenPair jwtTokenPair = jwtTokenGenerator.jwtTokenPair("133", roles, additional);

        System.out.println("jwtTokenPair = " + jwtTokenPair);
    }

}
