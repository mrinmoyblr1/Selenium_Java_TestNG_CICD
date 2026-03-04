package ExcelDriven;

import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class testSample {

    @Test
    public void testSample() throws IOException {
        dataDriven d = new dataDriven();

        ArrayList<String> data = d.getData("Add Profile");

        System.out.println(data.get(0));
        System.out.println(data.get(1));
        System.out.println(data.get(2));
        System.out.println(data.get(3));


        for (int i = 0; i < data.size(); i++) {
            System.out.println(data.get(i));
        }

        for (String a1 : data) {
            System.out.println(a1);
        }
    }
}
