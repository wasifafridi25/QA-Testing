import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestAmazonMultipleSearch {
    WebDriver driver;
    String myWebBrowserDriver = "webdriver.chrome.driver";
    String myDriverPath = "C:\\Drivers\\Selenium\\chrome\\chromedriver.exe";

    String url = "http://www.amazon.com/";
    int sleepTime = 5000;
    String msg = "not found the price meets my expectation ";

    String item1 = "PlayStation 5 game console";
    String price1a = "$499.99";
    String price1b = "$549.99";
    String price1c = "459.99";

    String item2 = "MacBook Pro";
    String price2a = "$1145.90";
    String price2b = "$1099";
    String price2c = "$2,449";

    String item3 = "men's sneakers";
    String price3a = "$159.99";
    String price3b = "$79.99";
    String price3c = "$52";

    String item4 = "Bluetooth headphones";
    String price4a = "$79.99";
    String price4b = "$149.99";
    String price4c = "$99.99";

    String item5 = "Tennis Racket";
    String price5a = "$29.99";
    String price5b = "$49.99";
    String price5c = "$249.99";


    @BeforeTest
    void setup() {
        System.setProperty(myWebBrowserDriver, myDriverPath);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @AfterTest
    void tearDown() {
        driver.quit();
    }

    @Test(priority = 0, enabled = true)
    void searchPlayStationComparePrices() throws InterruptedException {
        searchItem(item1, price1a, price1b, price1c);
    }

    @Test(priority = 1, enabled = true)
    void searchMenSneakersComparePrices() throws InterruptedException {
        searchItem(item3, price3a, price3b, price3c);
    }

    @Test(priority = 2, enabled = true)
    void searchMacBookProComparePrices() throws InterruptedException {
        searchItem(item2, price2a, price2b, price2c);
    }

    @Test(priority = 0, enabled = false)
    void searchBluetoothHeadphoneComparePrices() throws InterruptedException {
        searchItem(item4, price4a, price4b, price4c);
    }

    @Test(priority = 4, enabled = true)
    void searchTennisRacketComparePrices() throws InterruptedException {
        searchItem(item5, price5a, price5b, price5c);
    }

    void searchItem(String item, String priceA, String priceB, String priceC) throws InterruptedException {
        driver.findElement(By.id("twotabsearchtextbox")).clear();
        Thread.sleep(sleepTime);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(item);
        driver.findElement(By.id("nav-search-submit-button")).click();
        Thread.sleep(sleepTime);
        boolean b = driver.getPageSource().contains(priceA) ||
                driver.getPageSource().contains(priceB) ||
                driver.getPageSource().contains(priceC);
        Assert.assertTrue(b, msg + priceA + " or " + priceB + " or " + priceC);
    }

}
