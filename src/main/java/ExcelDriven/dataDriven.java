package ExcelDriven;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class dataDriven {
    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream("/Users/mrinmoy/Document-Local/Documents/Development_Local/Selenium_Java_TestNG_CICD/src/main/java/ExcelDriven");


        XSSFWorkbook workbook = new XSSFWorkbook(file);


        System.out.println("Data Driven Framework");


    }
}
