# JDBC

JDBC 即 Java DataBase Connectivity，是一套进行关系型数据库操作的标准，可以向下对接不同数据库，即一套为了兼容关系型数据库的接口标准。

JDBC 定义了所有关系型数据库的接口标准，而各个是数据库的厂商要自己去适配这套接口标准，各个厂商做出自己的具体实现。

真正使用时，我们调用 JDBC 接口，真正执行的则是对于数据库厂商的具体实现类。

使用步骤：
1. 导入数据库厂商提供的 jar 包，建议用项目管理工具引入。
2. 注册驱动
3. 获取数据库连接对象
4. 定义 sql
5. 获取执行 sql 语句的对象 Statement
6. 执行 sql，接受返回结果
7. 处理数据
8. 释放数据库连接对象

`Class.forName("com.mysql.cj.jdbc.Driver");` 将类导入，而这个类在初始化时就会运行一段静态内存区的代码，其源码如下：
```java
    static {
        try {
            java.sql.DriverManager.registerDriver(new Driver());
        } catch (SQLException E) {
            throw new RuntimeException("Can't register driver!");
        }
    }
```

可以看出，其行为只是调用了 java.sql.DriverManager.registerDriver(new Driver()); 这个函数，并添加了一些错误判断而已。

> MySQL5 之后有在 META-INF 文件中进行了自动注册，所以 mysql5 之后的驱动 jar 包可以省略注册驱动的步骤。


