import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class TestEbayDataProviderSearch {
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

    @Test(dataProvider = "eBaySearchDataProvider")
    public void searchItem (String myItem, String myPrice) {
        driver.findElement(By.id("gh-ac")).clear();
        driver.findElement(By.id("gh-ac")).sendKeys(myItem);
        driver.findElement(By.id("gh-btn")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        boolean b = driver.getPageSource().contains(myPrice);
        Assert.assertTrue(b, msg + myPrice);
        System.out.println(myItem+"            "+myPrice);
    }

    @DataProvider(name = "eBaySearchDataProvider")
    public Object[][] getData(){
        Object[][] data = { {"JBL Speakers", "$129.99"}, {"cell phones", "$69.04"}, {"womens shoes", "$41.97"} };
        return data;
    }
}
