package com.servletsimple.redirect;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重定向
 * 临时重定向：指的是浏览器第一次发请求，服务器返回 302 并返回 location 头部指引需要重定向的路径，浏览器收到 302 后再根据请求中的 location 请求新的路径
 */
@WebServlet(urlPatterns = "/NewPath")
public class NewPath extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain; charset=utf-8");
        ServletOutputStream outputStream = resp.getOutputStream();
        outputStream.write("重定向成功！这是新路径的返回！查看 network 中第一次请求的 302 吧！".getBytes("UTF-8"));
        outputStream.close();
    }
}
