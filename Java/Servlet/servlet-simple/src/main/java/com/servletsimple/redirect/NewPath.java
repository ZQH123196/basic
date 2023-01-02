package com.servletsimple.redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向
 * 临时重定向：指的是浏览器第一次发请求，服务器返回 302 并返回 location 头部指引需要重定向的路径，浏览器收到 302 后再根据请求中的 location 请求新的路径
 */
@WebServlet(urlPatterns = "OldPath")
public class OldPath extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        resp.setHeader("Location", "/NewPath");
    }
}
