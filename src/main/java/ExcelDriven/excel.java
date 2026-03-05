package ExcelDriven;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class excel {
    @Test
    public void getData() throws IOException {
//        Object[][] data = {{"Hello", " text", "1"}, {"bye", " message", "143"}, {"solo", " call", "454"}};
        // Every ROW of Excel should be one Object array
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/ExcelDriven/excelDriven.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);

        XSSFSheet sheet = wb.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();

        XSSFRow row = sheet.getRow(0); // It will return the first row of the sheet and we can get the column count from that row
        int column_Count = row.getLastCellNum();

        Object[][] data = new Object[rowCount - 1][column_Count];

        for (int i = 0; i < rowCount - 1; i++) {
            row = sheet.getRow(i + 1);

            for (int j = 0; j < column_Count; j++) {


                data[i][j] = row.getCell(j);
            }
        }
    }
}
