package com.example.jdk;

import cn.hutool.core.io.resource.ResourceUtil;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;


/**
 * 理解 java security 的设计
 * 秘钥有格式，比如 JDK 有内置实现的 PKCS8
 * 所以 java 使用 KeySpec 接口以及其多种实现作为多种秘钥格式的解析
 * 然后 KeyFactory 根据 KeySpec 来解析私钥并生成 Keypair 方便操作
 *
 * 因此使用时应为：privateKey -> KeySpec -> KeyFactory
 *
 */
@Slf4j
public class Test {



    @org.junit.jupiter.api.Test
    public void test() throws NoSuchAlgorithmException {
        byte[] privateKeyBytes = ResourceUtil.readBytes("openssl_key/private.pem");
        KeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory.getInstance("RSA").generatePrivate();




    }

}
