package ExcelDriven;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvider {


    @Test(dataProvider = "getData")
    public void testCaseData() {
    }


    @DataProvider(name = "getData")
    public Object[][] getData() {
        Object[][] data = {{"Hello", " text", 1}, {"bye", " message", 143}, {"solo", " call", 453}};
        return data;
    }
}
