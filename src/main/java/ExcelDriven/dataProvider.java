package ExcelDriven;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvider {


    @Test(dataProvider = "getData")
    public void testCaseData(String greeting, String message, int number) {
        System.out.println(greeting + message + " " + number);
    }


    @DataProvider(name = "getData")
    public Object[][] getData() {
        Object[][] data = {{"Hello", " text", 1}, {"bye", " message", 143}, {"solo", " call", 454}};
        return data;
    }
}
