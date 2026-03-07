package ExcelDriven;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class dataProvider {
    DataFormatter formatter = new DataFormatter();

    @Test(dataProvider = "getData")
    public void testCaseData(String greeting, String communication, String id) {
        System.out.println(greeting + communication + " " + id);
    }

    @DataProvider(name = "getData")
    public Object[][] getData() throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/ExcelDriven/excelDriven.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        // Getting the sheet from the workbook
        XSSFSheet sheet = wb.getSheetAt(0);

        // Getting the total number of rows in the sheet
        int rowCount = sheet.getPhysicalNumberOfRows();

        // Below two steps will get the total number of columns in the sheet
        XSSFRow row = sheet.getRow(0);
        int column_Count = row.getLastCellNum();

        // Create a 2D array to store the data from the sheet
        // We are using rowCount - 1 because we are skipping the header row
        // We are using column_Count because we want to store all the columns in the sheet
        // We are using Object[][] because we want to store different types of data in the sheet (String, int, etc.)
        // We are using Object[][] because we want to return the data from the method and use it in the test method
        Object[][] data = new Object[rowCount - 1][column_Count];
        for (int i = 0; i < rowCount - 1; i++) {
            row = sheet.getRow(i + 1);
            for (int j = 0; j < column_Count; j++) {
                XSSFCell cell = row.getCell(j);
                // This will format the cell value to string
                data[i][j] = formatter.formatCellValue(cell);
            }
        }
        return data;
    }
}
