<html>
<head>
    <title>Hello World - JSP</title>
</head>
<body>
<%-- JSP Comment

整个JSP的内容实际上是一个HTML，但是稍有不同：

包含在<%\-\-和\-\-%>之间的是JSP的注释，它们会被完全忽略；
包含在<%和%>之间的是Java代码，可以编写任意Java代码；
如果使用<%= xxx %>则可以快捷输出一个变量的值。


JSP页面内置了几个变量：

out：表示HttpServletResponse的PrintWriter；
session：表示当前HttpSession对象；
request：表示HttpServletRequest对象。

--%>
<h1>Hello World!</h1>
<p>
    <%
        out.println("Your IP address is ");
    %>
    <span style="color:red">
        <%= request.getRemoteAddr() %>
    </span>
</p>
</body>
</html>