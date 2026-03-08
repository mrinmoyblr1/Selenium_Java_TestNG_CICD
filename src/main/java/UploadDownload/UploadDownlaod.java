package UploadDownload;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;

public class UploadDownlaod {
    @Test(groups = {"BKM"})
    public void uploadDownload() throws InterruptedException, IOException {
        String fileName = System.getProperty("user.dir") + "/src/main/java/UploadDownload/download.xlsx";
        String fruitName = "Apple";
        String updatedValue = "598";
        String columnName = "price";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/upload-download-test/");
        driver.findElement(By.cssSelector("#downloadButton")).click();
        // Edit Excel - getColumnNumber of Price -getRowNUmber of Apple -> Update Excel Wow, Col
        // Update Excel Data
        int row = getRowNumber(fileName, fruitName);
        int col = getColNumber(fileName, columnName);
        Assert.assertTrue(updateCell(fileName, row, col, updatedValue));
        WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
        upload.sendKeys(System.getProperty("user.dir") + "/src/main/java/UploadDownload/download.xlsx");
        // Wait for Success Message to show up ad wait for disappearing
        By toastLocator = By.cssSelector(".Toastify__toast-body div:nth-child(2");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(toastLocator));
        String toastText = driver.findElement(toastLocator).getText();
        System.out.println(toastText);
        Assert.assertEquals(toastText, "Updated Excel Data Successfully.");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));
        // Verify updated Excel data showing in the Web page
        String priceColumn = driver.findElement(By.xpath("//div[text()='Price']")).getAttribute("data-column-id");
        //System.out.println(priceColumn);
        String actualPrice = driver.findElement(By.xpath("//div[text()='" + fruitName + "']/parent::div/parent::div/div[@id='cell-" + priceColumn + "-undefined']")).getText();
        // Below are two examples of xPath of above
        ////div[text()='Apple']/parent::div/following-sibling::div[2]
        ////div[text()='Apple']/parent::div/parent::div/div[@id='cell-4-undefined']
        System.out.println("The Actual Fruit Price: " + actualPrice);
        Assert.assertEquals(actualPrice, updatedValue);
        Thread.sleep(2000);
        driver.quit();
    }

    public int getRowNumber(String fileName, String fruitName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        ArrayList<String> a = new ArrayList<String>();
        FileInputStream file = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        // Identify the TestCases column by scanning the entire 1st row
        Iterator<Row> rows = sheet.iterator();  // sheet is a collection of rows
        int k = 0;
        int rowIndex = -1;
        while (rows.hasNext()) {
            // To get the first row
            Row row = rows.next();
            // To get the column number of TestCases
            Iterator<Cell> cell = row.cellIterator();
            while (cell.hasNext()) {
                Cell c = cell.next();
                if (c.getCellType() == CellType.STRING && c.getStringCellValue().equalsIgnoreCase(fruitName)) {
                    rowIndex = k;
                } else if (c.getCellType() == CellType.NUMERIC && formatter.formatCellValue(c).equalsIgnoreCase(fruitName)) {
                    rowIndex = k;
                }
            }
            k++;
        }
        return rowIndex;
    }

    public static int getColNumber(String fileName, String columnName) throws IOException {
        ArrayList<String> a = new ArrayList<String>();
        FileInputStream file = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        // Identify the TestCases column by scanning the entire 1st row
        Iterator<Row> rows = sheet.iterator();  // sheet is a collection of rows
        // To get the first row
        Row firstrow = rows.next();
        // To get the column number of TestCases
        Iterator<Cell> ce = firstrow.cellIterator(); // row is a collection of cells
        int k = 1;
        int column = 0;
        while (ce.hasNext()) {
            Cell value = ce.next();
            if (value.getStringCellValue().equalsIgnoreCase(columnName)) {
                column = k;
            }
            k++;
        }
        System.out.println(column);
        return column;
    }

    public boolean updateCell(String fileName, int row, int col, String value) throws IOException {
        ArrayList<String> a = new ArrayList<String>();
        FileInputStream file = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row rowField = sheet.getRow(row);
        Cell cellField = rowField.getCell(col - 1);
        cellField.setCellValue(value);
        //sheet.getRow(row).getCell(col).setCellValue(value);
        FileOutputStream fos = new FileOutputStream(new File(fileName));
        workbook.write(fos);
        workbook.close();
        fos.close();
        return true;
    }
}
