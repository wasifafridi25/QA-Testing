import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.time.Duration;

public class TestEbayParameterSearch {
    WebDriver driver;
    String driverPath = "C:\\Drivers\\Selenium\\chrome\\chromedriver.exe";

    String url = "http://www.ebay.com/";

    String msg = "not found the price meets my expectation ";

    @BeforeTest
    void setup(){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterTest
    void tearDown(){
        driver.quit();
    }

    @Test
    @Parameters({"item", "price"})
    void searchItem (String myItem, String myPrice) {
        driver.findElement(By.id("gh-ac")).clear();
        driver.findElement(By.id("gh-ac")).sendKeys(myItem);
        driver.findElement(By.id("gh-btn")).click();
        System.out.println("Searching " + myItem);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        boolean b = driver.getPageSource().contains(myPrice);
        Assert.assertTrue(b, msg + myPrice);
    }

}