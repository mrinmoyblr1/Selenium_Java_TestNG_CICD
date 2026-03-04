package ExcelDriven;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class dataDriven {
    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("user.dir" + "/src/main/java/ExcelDriven/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        int sheets = workbook.getNumberOfSheets();
        for (int i = 0; i < sheets; i++) {

            XSSFWorkbook sheets = workbook.getSheetAt(i);
            if (sheets.getSheetName().equalsIgnoreCase("TestData")) {
                System.out.println("Sheet found");


            }


        }
    }
