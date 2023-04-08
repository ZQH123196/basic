package com.eor.database.create;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.ConnectionPoolDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

/**
 *
 */
public class Create {

    int threadNum = Runtime.getRuntime().availableProcessors();
    List<Connection> connList = Collections.synchronizedList(new ArrayList<>(threadNum));
    String url = "";

    @BeforeAll
    public void beforeAll() throws SQLException {
        for (int i = 0; i < threadNum; i++) {
            Connection conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            connList.add(conn);
        }
    }

    @AfterAll
    public void afterAll() throws SQLException {
        for (Connection conn : connList) {
            conn.close();
        }
    }


    @Test
    public void t() {
        // 开局榨干连接池

    }


}
