package com.example.authenticationformlogin.securitysupport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 因为这个是认证失败的，很可能 security 的 userdetail 都没能初始化成功，因此只能从 HttpServletRequest 中摘取信息。
 */
@Component
@Slf4j
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {



    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = request.getHeader("username");

        if (exception instanceof AccountExpiredException) {
            // 账号过期
            log.info("[登录失败] - 用户[{}]账号过期", username);
        } else if (exception instanceof BadCredentialsException) {
            // 密码错误
            log.info("[登录失败] - 用户[{}]密码错误", username);
        } else if (exception instanceof CredentialsExpiredException) {
            // 密码过期
            log.info("[登录失败] - 用户[{}]密码过期", username);
        } else if (exception instanceof DisabledException) {
            // 用户被禁用
            log.info("[登录失败] - 用户[{}]被禁用", username);
        } else if (exception instanceof LockedException) {
            // 用户被锁定
            log.info("[登录失败] - 用户[{}]被锁定", username);
        } else if (exception instanceof InternalAuthenticationServiceException) {
            // 内部错误
            log.error(String.format("[登录失败] - [%s]内部错误", username), exception);
        } else {
            // 其他错误
            log.error(String.format("[登录失败] - [%s]其他错误", username), exception);
        }
    }
}
