package db.util_sqlite;

import org.sqlite.JDBC;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteUtils {

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
    public static Connection connectDb(String jdbcUrl) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // create a connection to the database
            conn = DriverManager.getConnection(jdbcUrl);
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
