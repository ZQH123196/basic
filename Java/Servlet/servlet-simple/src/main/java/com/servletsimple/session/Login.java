package com.servletsimple.session;

import org.apache.catalina.session.StandardSession;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * tomcat servlet 对于 session 的实现默认是利用 cookies 中一个 key 为 jsession 的值进行存储的
 * 登录后，查看浏览器网络就能看见 Set-Cookie: JSESSIONID=93B6FB5596EA32516C2AD42F90DD0709; Path=/; HttpOnly
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
    // 模拟一个数据库:
    private Map<String, String> users = new HashMap() {{
        put("eor", "1008610086");
    }};

    // GET 请求时显示 html 的登录页:
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.write("<h1>Sign In</h1>");
        pw.write("<form action=\"/Login\" method=\"post\">");
        pw.write("<p>Username: <input name=\"username\"></p>");
        pw.write("<p>Password: <input name=\"password\" type=\"password\"></p>");
        pw.write("<p><button type=\"submit\">Sign In</button> <a href=\"/\">Cancel</a></p>");
        pw.write("</form>");
        pw.flush();
    }

    /**
     * 处理上面登陆请求
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先清理 session
        req.getSession().removeAttribute("user");

        String username = req.getHeader("username");
        String password = req.getHeader("password");

        if (users.containsKey(username) && users.get(username).equals(password)) {
            req.getSession().setAttribute("user", username);
            resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
            resp.sendRedirect("/PreserverSession");
        }

    }
}