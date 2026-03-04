package ExcelDriven;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class dataProvider {
    @Test
    public void testCaseData() {
    }

    @DataProvider(name = "getData")
    public void getData() {
        Object[][] data = {{"Hello", " text", 1}, {"bye", " message", 143}, {"solo", " call", 453}};
    }
}
