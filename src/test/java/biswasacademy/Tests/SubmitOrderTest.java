package biswasacademy.Tests;

import biswasacademy.TestComponents.BaseTest;
import biswasacademy.pageObjects.*;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData", groups = {"Purchase"})
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {


        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));


        productCatalogue.addProductToCart(input.get("product"));

        CartPage cartPage = productCatalogue.goToCartPage();

        boolean match = cartPage.verifyProductDisplaying(input.get("product"));
        Assert.assertTrue(match);

        CheckoutPage checkoutPage = cartPage.goToCheckOut();
        checkoutPage.selectCountry("India");

        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmation = confirmationPage.getConfirmationMessage();
        System.out.println(confirmation);
        Assert.assertTrue(confirmation.equalsIgnoreCase("Thankyou for the order."));

        getScreenShot("submitOrder", driver);

        Thread.sleep(2000);
    }


    // To verify ZARA COAT 3 is displaying in the order summary page
    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest() throws InterruptedException {
        System.out.println("Order History Test...");
        ProductCatalogue productCatalogue = landingPage.loginApplication("mrinmoy.blr@gmail.com", "Test1234");
        OrderPage orderPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(orderPage.verifyOrderDisplaying(productName));
        Thread.sleep(3000);
    }


    @DataProvider(name = "getData")
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/biswasacademy/data/PurchaseOrder.json");
        return new Object[][]{{data.get(0)}, {data.get(1)}
        };
    }

}
