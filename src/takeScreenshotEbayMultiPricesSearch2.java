import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class takeScreenshotEbayMultiPricesSearch2 {
    WebDriver driver;
    String driverPath = "C:\\Drivers\\Selenium\\chrome\\chromedriver.exe";
    String screenshotPath = "c://CS522Screenshots";

    String url = "http://www.ebay.com/";
//    int sleepTime = 5000;
    String msg = "not found the price meets my expectation ";

    String item1 = "JBL Speakers";
    String price1a = "$65.45";
    String price1b = "$129.99";
    String price1c = "$299.99";

    String item2 = "cell phones";
    String price2a = "$325.90";
    String price2b = "$244.00";
    String price2c = "$69.78";

    String item3 = "womens shoes";
    String price3a = "$41.97";
    String price3b = "$29.99";
    String price3c = "$89.96";

    @BeforeTest
    void setup() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterTest
    void tearDown() {
        driver.quit();
    }

    @Test(priority = 3, enabled = true)
    void searchJBLSpeakersComparePrices() throws Exception {
        searchItem(item1, price1a, price1b, price1c);
        takeSnapShot(driver, "D://SFBU//Homework Screenshots") ;
        //(YearMonthDay_HourMinuteSecond)
    }

    @Test(priority = 1, enabled = true)
    void searchWomensShowsComparePrices() throws Exception {
        searchItem(item3, price3a, price3b, price3c);
        takeSnapShot(driver, "D://SFBU//Homework Screenshots") ;
    }

    @Test(priority = 2, enabled = false)
    void searchCellPhoneComparePrices() throws Exception {
        searchItem(item2, price2a, price2b, price2c);
        takeSnapShot(driver, "D://SFBU//Homework Screenshots" + "//" + "test2.png") ;
    }

    void searchItem(String item, String priceA, String priceB, String priceC) throws InterruptedException {
        driver.findElement(By.id("gh-ac")).clear();
        driver.findElement(By.id("gh-ac")).sendKeys(item);
        driver.findElement(By.id("gh-btn")).click();
//        Thread.sleep(sleepTime);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        boolean b = driver.getPageSource().contains(priceA) ||
                driver.getPageSource().contains(priceB) ||
                driver.getPageSource().contains(priceC);
        Assert.assertTrue(b, msg + priceA + " or " + priceB + " or " + priceC);
    }

//    void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception {
//        //Convert web driver object to TakeScreenshot
//        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
//
//        //Call getScreenshotAs method to create image file
//        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
//
//        //Move image file to new destination
//        File DestFile=new File(fileWithPath);
//
//        //Copy file at destination
//        FileUtils.copyFile(SrcFile, DestFile);
//    }

    void takeSnapShot(WebDriver webdriver, String directoryPath) throws Exception {
        // Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);

        // Call getScreenshotAs method to create image file
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);

        // Get current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedNow = now.format(formatter);

        // Construct the screenshot file path using the date-time string
        String fileWithPath = directoryPath + "//screenshot_" + formattedNow + ".png";

        // Move image file to new destination
        File DestFile = new File(fileWithPath);

        // Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
    }


}