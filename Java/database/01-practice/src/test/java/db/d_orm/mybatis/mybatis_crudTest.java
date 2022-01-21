package db.d_orm.mybatis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import javax.sql.DataSource;

import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.*;

class mybatis_crudTest {

    DataSource dataSource;

    @BeforeEach
    void setUp() {

        mybatis_config.createSessionFactory(dataSource);
    }

    @AfterEach
    void tearDown() {
    }

    public void dqlTest() {

    }

}