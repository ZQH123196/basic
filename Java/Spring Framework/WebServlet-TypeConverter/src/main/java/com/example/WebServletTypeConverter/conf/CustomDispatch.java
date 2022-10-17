package com.example.WebServletTypeConverter.conf;

import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.FrameworkServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomDispatch extends FrameworkServlet {
    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        request.getHeader("name");
        request.getQueryString();

    }
}
