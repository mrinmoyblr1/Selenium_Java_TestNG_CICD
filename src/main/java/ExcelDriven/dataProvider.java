package ExcelDriven;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

public class dataProvider {

    // Multiple sets of data to our tests
    // Array
    // 5 sets of data - > 5 times your test will run


    @Test
    public void testCaseData() {

    }


    @DataProvider(name = "getData")
    public void getData() {
        Iterator<String> it = null;
        while (it.hasNext()) {
            String data = it.next();
            System.out.println(data);
        }
    }


}
