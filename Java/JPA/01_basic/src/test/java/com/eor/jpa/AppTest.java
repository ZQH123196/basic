package com.eor.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AppTest {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoad() throws SQLException {
        System.out.println(dataSource.getConnection());
        System.out.println(dataSource.getClass());
    }



}