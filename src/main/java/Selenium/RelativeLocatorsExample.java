package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocatorsExample {
    public static void main(String[] args) throws InterruptedException {
        //System.setProperty("webdriver.chrome.driver", "/Users/mrinmoy/IdeaProjects/Introduction/src/main/java/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        WebElement nameEditBox = driver.findElement(By.cssSelector("[name='name']"));
        String labelName = driver.findElement(with(By.tagName("label")).above(nameEditBox)).getText();
        String labelName1 = driver.findElement(with(By.tagName("label")).straightAbove(nameEditBox)).getText();
        System.out.println(labelName);
        System.out.println(labelName1);

        WebElement IceCreamsLabel = driver.findElement(By.xpath("//*[contains(text(),'Check me')]"));
        driver.findElement(with(By.tagName("input")).toLeftOf(IceCreamsLabel)).click();
        Thread.sleep(2000);

        WebElement rdb = driver.findElement(By.id("inlineRadio1"));
        String rbdName = driver.findElement(with(By.tagName("label")).toRightOf(rdb)).getText();
        System.out.println(rbdName);

        WebElement dateOfBirth = driver.findElement(By.xpath("//*[contains(text(),'Date of Birth')]"));
        // This relative does not support flex html structure
        // Here we can use below or straightBelow
        driver.findElement(with(By.tagName("input")).below(dateOfBirth)).click();
//        driver.findElement(with(By.tagName("input")).straightBelow(dateOfBirth)).click();

        Thread.sleep(2000);
        driver.quit();
    }
}
