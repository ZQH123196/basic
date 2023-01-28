package com.example.jdk;

import lombok.extern.slf4j.Slf4j;

import java.security.KeyFactory;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

@Slf4j
public class Test {


    /**
     * 秘钥有格式，比如 JDK 有内置实现的 PKCS8
     * 所以 java 使用 KeySpec 接口以及其多种实现作为多种秘钥格式的解析
     * 然后 KeyFactory 根据 KeySpec 来解析私钥并生成 Keypair 方便操作
     */
    @org.junit.jupiter.api.Test
    public void test() {
//        KeySpec keySpec = new PKCS8EncodedKeySpec();
//        KeyFactory.getInstance("RSA").generatePrivate();
    }

}
