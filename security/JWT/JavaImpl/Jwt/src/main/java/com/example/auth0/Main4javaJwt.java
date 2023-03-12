package com.example.auth0;

import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.ResourceUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

public class Main4javaJwt {
    private RSAPrivateKey rsaPrivateKey = null;
    private RSAPublicKey rsaPublicKey = null;

    @Test
    public void test() {
        Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
        String token = JWT.create()
                .withIssuer("auth0")
                .sign(algorithm);
        System.out.println(token);
    }
}
