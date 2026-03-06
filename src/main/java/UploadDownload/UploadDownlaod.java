package UploadDownload;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class UploadDownlaod {
    @Test
    public void uploadDownload() {

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/upload-download-test/");


        System.out.println("Upload and Download");
        driver.quit();
    }
}
