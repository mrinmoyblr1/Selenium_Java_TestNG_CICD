package UploadDownload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class UploadDownlaod {

    @Test
    public void uploadDownload() throws InterruptedException {

        String fruitName = "Mango";

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/upload-download-test/");

        driver.findElement(By.cssSelector("#downloadButton")).click();
        // Edit Excel


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
        System.out.println(priceColumn);
        String actualPrice = driver.findElement(By.xpath("//div[text()='" + fruitName + "']/parent::div/parent::div/div[@id='cell-" + priceColumn + "-undefined']")).getText();
        // Below are two examples of xPath of above
        ////div[text()='Apple']/parent::div/following-sibling::div[2]
        ////div[text()='Apple']/parent::div/parent::div/div[@id='cell-4-undefined']
        System.out.println("The Actual Fruit Price: "+actualPrice);
        Assert.assertEquals(actualPrice, "999");





        Thread.sleep(2000);
        driver.quit();
    }
}
