package ExcelDriven;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class testSample {

    @Test
    public void testSample() throws IOException {
        dataDriven d = new dataDriven();

        ArrayList<String> data1 = d.getData("Purchase");

        for (String a1 : data1) {
            System.out.println(a1);
        }
    }
}
