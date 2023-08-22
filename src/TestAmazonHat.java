import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestAmazonHat {
    WebDriver driver;
    String myWebBrowserDriver = "webdriver.chrome.driver";
    String myWebBrowserDriverPath = "C:\\Drivers\\Selenium\\chrome\\chromedriver.exe";
    String urlAmazon = "http://www.amazon.com/" ;

    String mySearchItem = "Hat";
    String category = "Women";

    @BeforeClass
    public void setup() throws Exception, InterruptedException {
        System.setProperty(myWebBrowserDriver, myWebBrowserDriverPath);
        driver = new ChromeDriver();
        driver.get(urlAmazon);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        takeSnapShot(driver, "D://SFBU//Homework Screenshots");
        maximizeWindowSize();
    }

    @AfterClass
    public void tearDown() throws Exception {
        Thread.sleep(3000);
        closeExit();

    }

    @Test
    public void searchDataDrivenTest() throws Exception {
        searchUsingDataDriven();
        findSelectProduct();
        proceedToPurchase();
    }

    void maximizeWindowSize() throws Exception{
        driver.manage().window().maximize();
//        WebElement res = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("searchDropdownBox")));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        takeSnapShot(driver, "D://SFBU//Homework Screenshots") ;
    }
    void searchUsingDataDriven() throws Exception, IOException {
        FileInputStream file = new FileInputStream("C://Users//User//IdeaProjects//Trial//TestDataJin.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        int rowCount = sheet.getLastRowNum();

        for (int i = 0; i <= rowCount; i++) {
            XSSFRow currentRow = sheet.getRow(i);

            // Assuming category is in column 0 and keyword is in column 1
            category = currentRow.getCell(0).toString();
            mySearchItem = currentRow.getCell(1).toString();

            // Use the read data to perform the search
            selectCategory();
            searchProduct();
        }

        workbook.close();
        file.close();
    }
    void selectCategory() throws Exception{
        driver.findElement(By.id("searchDropdownBox")).sendKeys(category);
        WebElement res = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
        takeSnapShot(driver, "D://SFBU//Homework Screenshots") ;
    }

    void searchProduct() throws Exception{
        WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
        search.clear();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        search.sendKeys(mySearchItem);
        driver.findElement(By.id("nav-search-submit-button")).click();
        takeSnapShot(driver, "D://SFBU//Homework Screenshots") ;
    }

    void findSelectProduct() throws Exception {
        WebElement productModel = driver.findElement(By.partialLinkText("2 Packs Vintage Washed Distressed Baseball"));
        productModel.click();
        takeSnapShot(driver, "D://SFBU//Homework Screenshots");

//        // Wait for the color options to be visible
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        WebElement colorOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='variation_color_name']/ul[@role='radiogroup']/li[@title='Click to select Black+rosered']//span[@class='a-declarative']/span//button[@class='a-button-text']//img[@alt='Black+rosered']")));
//        colorOption.click();
//
//        takeSnapShot(driver, "D://SFBU//Homework Screenshots");

    }


    // Method to proceed with purchase until "Added to Cart"
    void proceedToPurchase() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html//input[@id='add-to-cart-button']")));
        addToCartButton.click();

        Thread.sleep(2000); // Waits for 2 seconds

        takeSnapShot(driver, "D://SFBU//Homework Screenshots");

        WebElement goToCartLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Go to Cart")));
        goToCartLink.click();
        takeSnapShot(driver, "D://SFBU//Homework Screenshots");

    }
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

    public void closeExit() throws Exception{
        Thread.sleep(5000);
        driver.quit();
    }
}

