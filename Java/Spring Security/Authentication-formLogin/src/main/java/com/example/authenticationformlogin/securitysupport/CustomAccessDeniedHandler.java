package com.example.authenticationformlogin.securitysupport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录用户没有权限访问的处理
 * 用户虽然登录了，但是权限不够访问某些资源，这时候就需要AccessDeniedHandler来处理了
 */
@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.warn("访问[{}]失败，{}={}", request.getRequestURI(), accessDeniedException.getCause(), accessDeniedException);
    }
}
