package example.sqlite;


import org.sqlite.JDBC;
import org.sqlite.SQLiteConnection;
import org.sqlite.core.DB;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqliteDemo {
    public static void main(String[] args) throws SQLException {
        Path projectDir = Paths.get(".").toAbsolutePath().normalize();
        System.out.println(projectDir);

        Path targetDbUrl = Paths.get(projectDir.toString(), "src/main/resources/database/sqlite/test1.db").toAbsolutePath();
        System.out.println(targetDbUrl);

        String targetJdbcUrl = JDBC.PREFIX + targetDbUrl.toString();
        System.out.println(targetJdbcUrl);

        Connection conn = openConnectDb(targetJdbcUrl);
        String sqlTemplate = "select * from user_list";
        PreparedStatement statement = conn.prepareStatement(sqlTemplate);
        ResultSet resultSet = statement.executeQuery();
        String[] colLabel = new String[]{"userName", "password", "role", "locked"};
        String formatTemplate = "%s: [%s]";

        List<Map<String, Object>> resList = new ArrayList<>();
        while (resultSet.next()) {
            Map<String, Object> perRowMap = new LinkedHashMap<>();
            for (int i = 0; i < colLabel.length; i++) {
                String resValue = resultSet.getString(colLabel[i]);
                perRowMap.put(colLabel[i], resValue);
                String formatedStr = String.format(formatTemplate, colLabel[i], resValue);
                System.out.println(formatedStr);
            }
            resList.add(perRowMap);
            System.out.println("------");
        }
        conn.close();

        System.out.println("resList = " + resList);

        AutoTestManger(resList);

    }

    /**
     * 读取 locked 为 False 的 userName、password 和 role，然后将 locked 修改为 TRUE，
     * 跑完自动化测试后将 locked 再修改为 FALSE。
     */
    public static void AutoTestManger(List<Map<String, Object>> targetList) {
        for (Map<String, Object> map : targetList) {
            String locked = (String) map.get("locked");
            if (locked.equalsIgnoreCase("FALSE")) {
                map.put("locked", "TRUE");
                autoTest(map);
                map.put("locked", "FALSE");
            }
        }
    }

    private static void autoTest(Map map) {
        System.out.println("正在运行自动化测试！运行目标为：" + map);
    }


    public static Connection openConnectDb(String jdbcUrl) {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(jdbcUrl);
        } catch (RuntimeException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

}
