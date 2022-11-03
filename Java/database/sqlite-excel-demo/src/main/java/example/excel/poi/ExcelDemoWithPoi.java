package example.excel.poi;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ExcelDemoWithPoi {
    public static void main(String[] args) throws IOException {
        Path projectDir = Paths.get(".").toAbsolutePath().normalize();
        System.out.println(projectDir);

        Path targetFilePath = projectDir.resolve("src/main/resources/excel/userList.xlsx");
        System.out.println(targetFilePath);

        File targetFile = targetFilePath.toFile();
        InputStream inputStream = new FileInputStream(targetFile);


//        Workbook workbook = new HSSFWorkbook(inputStream);
        Workbook workbook = WorkbookFactory.create(inputStream);
        Sheet sheet1 = workbook.getSheet("Sheet1");
        Iterator<Row> rowIterator = sheet1.rowIterator();

        // 读取首行
        Row firstRow = rowIterator.next();
        // 获取 locked 列的位置
        int userName_ColumnIndex = -1;
        int password_ColumnIndex = -1;
        int role_ColumnIndex = -1;
        int locked_ColumnIndex = -1;
        for (int i = 0; i < firstRow.getLastCellNum(); i++) {
            Cell cell = firstRow.getCell(i);
            String cellValue = cell.getStringCellValue();
            if (cellValue.equalsIgnoreCase("userName")) {
                userName_ColumnIndex = cell.getColumnIndex();
            }
            if (cellValue.equalsIgnoreCase("password")) {
                password_ColumnIndex = cell.getColumnIndex();
            }
            if (cellValue.equalsIgnoreCase("role")) {
                role_ColumnIndex = cell.getColumnIndex();
            }
            if (cellValue.equalsIgnoreCase("locked")) {
                locked_ColumnIndex = cell.getColumnIndex();
            }
        }
        System.out.println("userName_ColumnIndex = " + userName_ColumnIndex);
        System.out.println("password_ColumnIndex = " + password_ColumnIndex);
        System.out.println("role_ColumnIndex = " + role_ColumnIndex);
        System.out.println("locked_ColumnIndex = " + locked_ColumnIndex);


        List<Map<String, Object>> resList = new ArrayList<>();
        // 读取剩余行
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Map<String, Object> perRowMap = new LinkedHashMap<>();

            DataFormatter dataFormatter = new DataFormatter();
            String userName = dataFormatter.formatCellValue(row.getCell(userName_ColumnIndex));
            String password = dataFormatter.formatCellValue(row.getCell(password_ColumnIndex));
            String role = dataFormatter.formatCellValue(row.getCell(role_ColumnIndex));
            String locked = dataFormatter.formatCellValue(row.getCell(locked_ColumnIndex));

            perRowMap.put("userName", userName);
            perRowMap.put("password", password);
            perRowMap.put("role", role);
            perRowMap.put("locked", locked);

            resList.add(perRowMap);
        }

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
}
