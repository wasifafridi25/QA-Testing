import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestAmazonSearch {
    WebDriver driver;
    String myWebBrowserDriver = "webdriver.chrome.driver";
    String myWebBrowserDriverPath = "C:\\Drivers\\Selenium\\chrome\\chromedriver.exe";
    String urlAmazon = "http://www.amazon.com/" ;
    String urlEbay = "http://www.ebay.com/";
    String mySearchItem = "Smart TV";
    String category = "Electronics";

    // add functional methods here
    void launchBrowserGoToAmazon() throws InterruptedException {
        System.setProperty(myWebBrowserDriver, myWebBrowserDriverPath);
        driver = new ChromeDriver();
        driver.get(urlAmazon);
        Thread.sleep(2000);
    }

    void maximizeWindowSize() throws InterruptedException {
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    void selectCategory() throws InterruptedException{
        driver.findElement(By.id("searchDropdownBox")).sendKeys(category);
        Thread.sleep(2000);
    }

    void searchProduct() throws InterruptedException{
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(mySearchItem);
        driver.findElement(By.id("nav-search-submit-button")).click();
        Thread.sleep(3000);
    }

    void openLinkBestSellers() throws InterruptedException{
        driver.findElement(By.linkText("Best Sellers")).click();
        Thread.sleep(2000);
    }

    void navigateToEbayGoBack() throws InterruptedException{
        driver.navigate().to(urlEbay);
        Thread.sleep(3000);
        driver.navigate().back();
        Thread.sleep(3000);
    }

    public void closeExit() {
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        // (10%)
        TestAmazonSearch obj = new TestAmazonSearch ();

        obj.launchBrowserGoToAmazon();
        obj.maximizeWindowSize();
        obj.selectCategory();
        obj.searchProduct();
        obj.openLinkBestSellers();
        obj.navigateToEbayGoBack();
        obj.closeExit();
    }
}


