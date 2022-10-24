package db.a_jdbc;

import static org.junit.jupiter.api.Assertions.*;

import db.util_sqlite.SqliteUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sqlite.JDBC;

import javax.sql.DataSource;
import java.sql.*;

@Slf4j
class Jdbc_crudTest {
    static Connection conn = null;
    static final String baseDbUrl = JDBC.PREFIX+"D:/software/sqlite/";
    // jdbc:sqlite:D:/software/sqlite/basic_java_database.db
    @BeforeEach
    void setUp() throws SQLException {

        conn = SqliteUtils.connectDb(baseDbUrl+"basic_java_database.db");
        conn.setAutoCommit(false);
        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
    }

    @AfterEach
    void tearDown() throws SQLException {
        SqliteUtils.closeConnDb(conn);
    }

    @Test
    void simple() throws SQLException {
        ddl();
        dql();
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
        // true if the first result is a ResultSet object;
        // false if it is an update count or there are no results
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




}