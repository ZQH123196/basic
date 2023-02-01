package com.example.springsecurity.jwt.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * accessToken 接入的 token
 * refreshToken 自动续期的 token
 *
 */
@Data
public class JwtTokenPair implements Serializable {
    private static final long serialVersionUID = -8518897818107784049L;
    private String accessToken;
    private String refreshToken;
}
