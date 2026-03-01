package biswasacademy.Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StandAlone {
    public static void main(String[] args) throws InterruptedException {
        String productName = "ZARA COAT 3";
        System.out.println("1========Test Started========1");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.get("https://rahulshettyacademy.com/client/#/auth/login");
        System.out.println(driver.getTitle());


        driver.findElement(By.id("userEmail")).sendKeys("mrinmoy.blr@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Anjali@12");
        driver.findElement(By.id("login")).click();


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("card-body")));
        List<WebElement> products = driver.findElements(By.className("card-body"));
        WebElement prod = products.stream().
                filter(product -> product.findElement(By.cssSelector("b"))
                        .getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
        //prod.findElement(By.cssSelector(".card-body button:first-of-type")).click(); // Here :first-of-type is used to select the first button (Add to Wishlist)


        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click(); // Here :last-of-type is used to select the last button (Add to Cart)
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        // Below is another way of writing the same wait condition using Lambda function
        wait.until(driver1 -> driver1.findElement(By.cssSelector("#toast-container")).isDisplayed());
        System.out.println(driver.findElement(By.cssSelector("#toast-container")).getText());
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[routerlink*='cart']")));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        System.out.println(match);
        Assert.assertTrue(match);


        driver.findElement(By.cssSelector("li[class='totalRow'] button[type='button']")).click();
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
        // This is a very important cssSelector
        //    .ta-item:nth-of-type(2)
        // Below is a alternative xPath selector
        //    //button[contains(@class,'ta-item')][2]
        driver.findElement(By.cssSelector(".action__submit")).click();
        String confirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();
        System.out.println(confirmation);
        Assert.assertTrue(confirmation.equalsIgnoreCase("Thankyou for the order."));
        Thread.sleep(2000);
        driver.quit();
    }
}
