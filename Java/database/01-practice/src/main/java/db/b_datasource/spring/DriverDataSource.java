package db.b_datasource.spring;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import javax.sql.DataSource;
import java.sql.Driver;


/**
 * https://docs.spring.io/spring-framework/docs/5.3.10/reference/html/data-access.html#jdbc
 * 不要将该类中的任何方法用于生产！
 * 	You should use the DriverManagerDataSource and SimpleDriverDataSource classes (as included in the Spring distribution) only for testing purposes!
 * 	Those variants do not provide pooling and perform poorly when multiple requests for a connection are made.
 */
public class DriverDataSource {

    /**
     * 非连接池的方式，每次都新建一个连接
     * @param driverClassName
     * @param url
     * @param username
     * @param password
     * @return
     */
    public DataSource createDataSource(String driverClassName, String url, String username, String password) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(url, username, password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    public DataSource createDataSource(Driver driver, String url, String username, String password) {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource(driver, url, username, password);
        return dataSource;
    }


}
