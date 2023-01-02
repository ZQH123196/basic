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
@WebServlet(urlPatterns = "/OldPath")
public class OldPath extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 301 永久重定向
//        resp.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);

        // 可以使用 http head 完成重定向，或者使用高级 api
//        resp.setHeader("Location", "/NewPath");
        resp.sendRedirect("/NewPath");
    }
}
