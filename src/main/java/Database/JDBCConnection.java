package Database;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.sql.*;

public class JDBCConnection {

    @Test
    public void test() throws SQLException {
        String host = "localhost";
        String port = "3306";

        Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/demo", "root", "root");
        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM credentials WHERE scenario ='Q1'");

        while (rs.next()) {
            System.out.println(rs.getString("Username"));
            System.out.println(rs.getString("Password"));
            String username = rs.getString("username");
            String password = rs.getString("password");

            {
                WebDriver driver = new FirefoxDriver();
                driver.get("https://login.salesforce.com");
                driver.findElement(By.xpath(".//*[@id='username']")).sendKeys(username);
                driver.findElement(By.xpath(".//*[@id='password']")).sendKeys(password);

            }
        }
    }
}
