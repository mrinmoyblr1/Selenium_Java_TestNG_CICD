package UploadDownload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class UploadDownlaod {
    @Test
    public void uploadDownload() throws InterruptedException {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/upload-download-test/");

        driver.findElement(By.id("downloadButton")).click();


        System.out.println("Upload and Download");
        Thread.sleep(4000);
        driver.quit();
    }
}
