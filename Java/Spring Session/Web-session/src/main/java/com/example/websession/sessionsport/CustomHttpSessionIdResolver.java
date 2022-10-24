package com.example.websession.sessionsport;

import lombok.extern.slf4j.Slf4j;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/** 自定义Session解析器
 * 官方实现了Cookie和 Session的解析，但在实际的项目中，还会遇到 token 拼接到 URL 上的情况，
 * 这时候可以实现 HttpSessionIdResolver 接口，可以参考官方 org.springframework.session.web.http.CookieHttpSessionIdResolver。
 * 支持 sessionId 存到 cookie，header 和 request parameter
 */
@Slf4j
public class CustomHttpSessionIdResolver implements HttpSessionIdResolver {

    private String sessionIdName = SessionConstants.AUTH_TOKEN.tokenName;

    private CookieHttpSessionIdResolver cookieHttpSessionIdResolver;

    public CustomHttpSessionIdResolver() {
        this.cookieHttpSessionIdResolver = new CookieHttpSessionIdResolver();
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName(this.sessionIdName);
        this.cookieHttpSessionIdResolver.setCookieSerializer(cookieSerializer);
    }


    @Override
    public List<String> resolveSessionIds(HttpServletRequest request) {
        // cookie
        List<String> cookies = cookieHttpSessionIdResolver.resolveSessionIds(request);
        return cookies;
    }

    @Override
    public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        log.info(sessionIdName + "={}", sessionId);
        response.setHeader(this.sessionIdName, sessionId);
        this.cookieHttpSessionIdResolver.setSessionId(request, response, sessionId);
    }

    @Override
    public void expireSession(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(this.sessionIdName, "");
        this.cookieHttpSessionIdResolver.setSessionId(request, response, "");
    }
}
