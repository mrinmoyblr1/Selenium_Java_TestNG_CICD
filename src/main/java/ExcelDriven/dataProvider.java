package ExcelDriven;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class dataProvider {


    @Test(dataProvider = "getData")
    public void testCaseData(String greeting, String communication, String id) {
        System.out.println(greeting + communication + " " + id);
    }


    @DataProvider(name = "getData")
    public Object[][] getData() throws IOException {
        Object[][] data = {{"Hello", " text", "1"}, {"bye", " message", "143"}, {"solo", " call", "454"}};
        // Every ROW of Excel should be one Object array

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/ExcelDriven/excelDriven.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {

        }


        return data;
    }
}
