package ExcelDriven;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class dataProvider {


    @Test(dataProvider = "getData")
    public void testCaseData(String greeting, String communication, String id) {
        System.out.println(greeting + communication + " " + id);
    }


    @DataProvider(name = "getData")
    public void getData() throws IOException {
//        Object[][] data = {{"Hello", " text", "1"}, {"bye", " message", "143"}, {"solo", " call", "454"}};
        // Every ROW of Excel should be one Object array

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/ExcelDriven/excelDriven.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);

        XSSFSheet sheet = wb.getSheetAt(0);
        int rowCount = sheet.getPhysicalNumberOfRows();

        XSSFRow row = sheet.getRow(0);
        int column_Count = row.getLastCellNum();

        Object[][] data = new Object[rowCount - 1][column_Count];


    }
}
