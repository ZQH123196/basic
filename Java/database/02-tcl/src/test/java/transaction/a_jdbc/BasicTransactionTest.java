package transaction.a_jdbc;

import db.util_sqlite.SqliteUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.sqlite.JDBC;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class BasicTransactionTest {
    Connection conn = null;

    @BeforeEach
    void setUp() throws SQLException {
        Path targetDbPath = Paths.get("./src/main/resources/database/sqlite/BasicTransaction.db").toAbsolutePath().normalize();
        String jdbcUrl = JDBC.PREFIX + targetDbPath;
        conn = SqliteUtils.connectDb(jdbcUrl);
        // 1. 关闭自动提交
        conn.setAutoCommit(false);
    }

    @AfterEach
    void tearDown() throws SQLException {
        conn.close();
    }

    /**
     * 1.
     */
    @Test
    void testBasicTransaction() {
//        conn.prepareStatement()
    }


}