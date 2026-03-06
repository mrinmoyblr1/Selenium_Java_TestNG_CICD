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

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/upload-download-test/");

        driver.findElement(By.cssSelector("#downloadButton")).click();
        // Edit Excel

        WebElement upload = driver.findElement(By.cssSelector("input[type='file']"));
        upload.sendKeys(System.getProperty("user.dir") + "/src/main/java/UploadDownload/download.xlsx");


        // Wait for Success Message to show up ad wait for disappearing
        // Below is the Implicit wait

        By toastLocator = By.cssSelector(".Toastify__toast-body dev:nth-child(2)");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(toastLocator));


        String toastText = driver.findElement(toastLocator).getText();
        Assert.assertEquals(toastText, "Updated Excel Data Successfully.");


        // Verify updated Excel data showing in the Web page


        Thread.sleep(10000);
        driver.quit();
    }
}
