package com.example.authenticationformlogin.securitysupport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Called when a user has been successfully authenticated
 * 这里的 onAuthenticationSuccess 有一个重载的同名接口默认函数，那个接口默认函数是一个 模板方法。
 */
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("用户 [{}] 访问 [{}] 鉴权成功！", authentication.getName(), request.getRequestURI());
    }
}
