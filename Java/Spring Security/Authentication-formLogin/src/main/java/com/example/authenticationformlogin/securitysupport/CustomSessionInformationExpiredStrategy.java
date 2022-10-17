package com.example.authenticationformlogin.securitysupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import java.io.IOException;

/**
 * session 过期的策略
 * session 过期了可以重定向到特定的 url，比如 org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy
 * 下面就是直接抄的 SimpleRedirectSessionInformationExpiredStrategy
 */
public class CustomSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {

    private final Log logger = LogFactory.getLog(getClass());

    private final String destinationUrl;

    private final RedirectStrategy redirectStrategy;

    public CustomSessionInformationExpiredStrategy(String invalidSessionUrl) {
        this(invalidSessionUrl, new DefaultRedirectStrategy());
    }

    public CustomSessionInformationExpiredStrategy(String invalidSessionUrl, RedirectStrategy redirectStrategy) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        this.logger.debug("Redirecting to '" + this.destinationUrl + "'");
        this.redirectStrategy.sendRedirect(event.getRequest(), event.getResponse(), this.destinationUrl);
    }

}
