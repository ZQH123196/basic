package com.servletsimple.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * tomcat servlet 对于 session 的实现默认是利用 cookies 中一个 key 为 jsession 的值进行存储的
 */
@WebServlet("/LoginOut")
public class LoginOut extends HttpServlet {

    /**
     * 处理上面登陆请求
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 从HttpSession移除用户名:
        req.getSession().removeAttribute("user");
        resp.sendRedirect("/");

    }
}
