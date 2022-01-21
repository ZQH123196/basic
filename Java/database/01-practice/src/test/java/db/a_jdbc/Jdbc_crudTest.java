package db.a_jdbc;

import static org.junit.jupiter.api.Assertions.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.*;

@Slf4j
class Jdbc_crudTest {
    static Connection conn = null;
    static final String baseDbUrl = "jdbc:sqlite:D:/software/sqlite/";
    // jdbc:sqlite:D:/software/sqlite/basic_java_database.db
    @BeforeEach
    void setUp() throws SQLException {

        conn = connectDb(baseDbUrl+"basic_java_database.db");
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(TransactionIsolationLevel.SERIALIZABLE.getLevel());
    }

    @AfterEach
    void tearDown() throws SQLException {
        closeConnDb(conn);
    }

    @Test
    void simple() {
    }

    @Test
    void ddl() throws SQLException {
        String sqlDrop = "drop table if exists sys_user";
        Statement statement = conn.createStatement();

        boolean dropRes = statement.execute(sqlDrop);

        String sqlCreate = "create table sys_user\n" +
                "(\n" +
                "    user_id     bigint auto_increment comment '用户ID'\n" +
                "        primary key,\n" +
                "    dept_id     bigint                    null ,\n" +
                "    user_name   varchar(30)               not null,\n" +
                "    nick_name   varchar(30)               not null,\n" +
                "    user_type   varchar(2)   default '00' null,\n" +
                "    email       varchar(50)  default ''   null,\n" +
                "    phonenumber varchar(11)  default ''   null,\n" +
                "    sex         char         default '0'  null,\n" +
                "    avatar      varchar(100) default ''   null,\n" +
                "    password    varchar(100) default ''   null,\n" +
                "    status      char         default '0'  null,\n" +
                "    del_flag    char         default '0'  null,\n" +
                "    login_ip    varchar(128) default ''   null,\n" +
                "    login_date  datetime                  null,\n" +
                "    create_by   varchar(64)  default ''   null,\n" +
                "    create_time datetime                  null,\n" +
                "    update_by   varchar(64)  default ''   null,\n" +
                "    update_time datetime                  null,\n" +
                "    remark      varchar(500)              null\n" +
                ")";
        boolean createRes = statement.execute(sqlCreate);
        conn.commit();
        log.debug("createRes = [{}]", createRes);
        assertFalse(createRes);
    }

    @Test
    void dql() throws SQLException {
        String sqlStr = "select 1";
        PreparedStatement preparedStatement = conn.prepareStatement(sqlStr);
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        int res = resultSet.getInt(1);

        log.debug("res = {}", res);
    }

    @Test
    void dml() {

    }



    @Test
    void dcl() {
    }

    @Test
    void tcl() {
    }



    public static void createNewDatabase(String targetUrl) {
        String url = targetUrl;
        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // sqlite 连接可以自动创建
    public static Connection connectDb(String targetUrl) {
        Connection conn = null;
        String url = targetUrl;
        try {
            Class.forName("org.sqlite.JDBC");
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnDb(Connection conn) throws SQLException {
        conn.commit();
        if (!conn.isClosed()) {
            conn.close();
        }
    }
}