package com.example.authenticationformlogin.securitysupport;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * session 过期的策略
 * session 过期了可以重定向到特定的 url，比如 org.springframework.security.web.session.SimpleRedirectInvalidSessionStrategy
 */
public class A implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {

    }
}
