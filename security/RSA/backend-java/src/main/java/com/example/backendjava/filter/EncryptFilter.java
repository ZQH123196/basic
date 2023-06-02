package com.example.backendjava.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class EncryptFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        Boolean encryptHeader = Boolean.valueOf(req.getHeader("encryptHeader"));
        if (encryptHeader) preDecrypt(req);
        filterChain.doFilter(servletRequest, servletResponse);
        if (encryptHeader) postEncrypt((HttpServletResponse) servletResponse);

    }


    private void preDecrypt(HttpServletRequest req) throws IOException {


    }

    private void postEncrypt(HttpServletResponse res) {
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
