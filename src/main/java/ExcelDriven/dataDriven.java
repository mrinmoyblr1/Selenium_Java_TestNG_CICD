package ExcelDriven;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class dataDriven {
    public ArrayList<String> getData(String testCasesName) throws IOException {
        // Identify TestCases column by scanning the entire 1st row
        // Once column is identified then scan entire TestCases column to identify purchase test case row
        // After you grab purchase test case row then pull all the data of that row and feed into test
        ArrayList<String> a = new ArrayList<String>();
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/ExcelDriven/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase("TestData")) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                // Identify TestCases column by scanning the entire 1st row
                Iterator<Row> rows = sheet.iterator();  // sheet is collection of rows
                Row firstrow = rows.next();
                Iterator<Cell> ce = firstrow.cellIterator(); // row is collection of cells
                int k = 0;
                int column = 0;
                while (ce.hasNext()) {
                    Cell value = ce.next();
                    if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {
                        column = k;
                        // Desired column
                    }
                    k++;
                }
                // Once column is identified then scan entire TestCases column to identify purchase test case row
                while (rows.hasNext()) {
                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCasesName)) {
                        // After you grab purchase test case row then pull all the data of that row and feed into test
                        Iterator<Cell> cv = r.cellIterator();
                        int x = 0;
                        while (cv.hasNext()) {
                            a.add(cv.next().getStringCellValue());
                        }
                    }
                }
            }
        }
        return a;
    }

    public static void main(String[] args) throws IOException {
        dataDriven d = new dataDriven();
        ArrayList<String> data = d.getData("Purchase");
        for (String s : data) {
            System.out.println("Under Main Method: Test Data: " + s);
        }
    }
}
