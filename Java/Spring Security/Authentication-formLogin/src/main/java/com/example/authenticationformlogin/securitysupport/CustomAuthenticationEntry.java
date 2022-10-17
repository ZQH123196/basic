package com.example.authenticationformlogin.securitysupport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticationEntryPoint是 Spring Security Web 一个认证概念模型接口，顾名思义，他所建模的概念是:“认证入口点”。
 * 所以 AuthenticationEntryPoint 有很多的实现，这里的是用于异常处理的入口点。
 * 它在用户请求处理过程中遇到认证异常时，被ExceptionTranslationFilter用于开启特定认证方案(authentication schema)的认证流程。
 */
@Slf4j
@Component
public class CustomAuthenticationEntry implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.warn("访问[{}]失败，{}={}", request.getRequestURI(), authException.getCause(), authException);
    }
}
