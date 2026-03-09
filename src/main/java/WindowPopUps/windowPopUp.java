package WindowPopUps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class windowPopUp {
    @Test
    public void windowPopUp() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://admin:admin@the-internet.herokuapp.com/");
        driver.manage().window().maximize();

        driver.findElement(By.linkText("Basic Auth")).click();
    }
}
