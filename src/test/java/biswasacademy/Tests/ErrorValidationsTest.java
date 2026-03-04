package biswasacademy.Tests;

import biswasacademy.TestComponents.BaseTest;
import biswasacademy.TestComponents.Retry;
import biswasacademy.pageObjects.CartPage;
import biswasacademy.pageObjects.ProductCatalogue;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ErrorValidationsTest extends BaseTest {


    @Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
    public void LoginErrorValidation() {
        System.out.println("loginErrorValidation");

        landingPage.loginApplication("mrinmoy.blr@gmail.com", "Test1234");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
        System.out.println("Error message displayed" + ": " + landingPage.getErrorMessage());
    }


    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException {
        System.out.println("productErrorValidation");
        String productName = "ZARA COAT 3";

        ProductCatalogue productCatalogue = landingPage.loginApplication("mrinmoy.blr@gmail.com", "Test1234");
        productCatalogue.addProductToCart(productName);

        CartPage cartPage = productCatalogue.goToCartPage();
        boolean match = cartPage.verifyProductDisplaying("ZARA COAT 4");
        Assert.assertFalse(match);
    }
}
