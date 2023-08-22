//package StepDefinition;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.apache.commons.io.FileUtils;
//import org.openqa.selenium.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//public class projectDemo {
//    WebDriver driver;
//    String myWebBrowserDriver = "webdriver.chrome.driver";
//    String myWebBrowserDriverPath = "C:\\Drivers\\Selenium\\chrome\\chromedriver.exe";
//    String urlAmazon = "http://www.amazon.com/" ;
//
//    String mySearchItem = "Macbook Pro";
//    String category = "Electronics";
//
//    @Given("I am on the Amazon website")
//    public void setup() throws Exception, InterruptedException {
//        System.setProperty(myWebBrowserDriver, myWebBrowserDriverPath);
//        driver = new ChromeDriver();
//        driver.get(urlAmazon);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        takeSnapShot(driver, "D://SFBU//Homework Screenshots");
//        maximizeWindowSize();
//    }
//
//    void maximizeWindowSize() throws Exception{
//        driver.manage().window().maximize();
////        WebElement res = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("searchDropdownBox")));
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        takeSnapShot(driver, "D://SFBU//Homework Screenshots") ;
//    }
//    @When("I search for a product")
//    public void i_search_for_a_product() throws Exception, IOException {
//        FileInputStream file = new FileInputStream("C://Users//User//IdeaProjects//Trial//TestData.xlsx");
//        XSSFWorkbook workbook = new XSSFWorkbook(file);
//        XSSFSheet sheet = workbook.getSheet("Sheet1");
//
//        int rowCount = sheet.getLastRowNum();
//
//        for (int i = 0; i <= rowCount; i++) {
//            XSSFRow currentRow = sheet.getRow(i);
//
//            // Assuming category is in column 0 and keyword is in column 1
//            category = currentRow.getCell(0).toString();
//            mySearchItem = currentRow.getCell(1).toString();
//
//            // Use the read data to perform the search
//            selectCategory();
//            searchProduct();
//        }
//
//        workbook.close();
//        file.close();
//    }
//    void selectCategory() throws Exception{
//        driver.findElement(By.id("searchDropdownBox")).sendKeys(category);
//        WebElement res = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox")));
//        takeSnapShot(driver, "D://SFBU//Homework Screenshots") ;
//    }
//
//    void searchProduct() throws Exception{
//        WebElement search = driver.findElement(By.id("twotabsearchtextbox"));
//        search.clear();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//        search.sendKeys(mySearchItem);
//        driver.findElement(By.id("nav-search-submit-button")).click();
//        takeSnapShot(driver, "D://SFBU//Homework Screenshots") ;
//    }
//
//    @And("I select a product")
//    public void i_select_a_product() throws Exception {
//        // Locate the product by model (this would depend on the HTML structure)
//        WebElement productModel = null;
//
//        try {
//            productModel = driver.findElement(By.partialLinkText("Apple 2023 MacBook Pro Laptop M2 Pro chip with 12‑core CPU"));
//        } catch (NoSuchElementException e) {
//            // If the first element is not found, try finding the second element
//            productModel = driver.findElement(By.partialLinkText("Apple 2022 MacBook Pro Laptop with M2 chip"));
//        }
//        Thread.sleep(3000);
//        productModel.click();
//        takeSnapShot(driver, "D://SFBU//Homework Screenshots");
//        Thread.sleep(3000);
//
//        WebElement differentMac = driver.findElement(By.partialLinkText("MacBook Air 15.3\" (M2)"));
//        differentMac.click();
//        takeSnapShot(driver, "D://SFBU//Homework Screenshots");
//
//        //color
//        WebElement colorOption = driver.findElement(By.id("a-autoid-16"));
//        colorOption.click();
//        Thread.sleep(3000); // Waits for 3 seconds
//        takeSnapShot(driver, "D://SFBU//Homework Screenshots");
//
//        //capacity
//        WebElement capacityOption = driver.findElement(By.xpath("//div[@id='variation_size_name']/ul[@role='radiogroup']/li[@title='Click to select 512GB']//span[@class='a-declarative']/span//button[@class='a-button-text']//p[.='512GB']"));
//        capacityOption.click();
//        Thread.sleep(3000);
//        takeSnapShot(driver, "D://SFBU//Homework Screenshots");
//        Thread.sleep(3000); // Waits for 3 seconds
//
//
//    }
//
//    @And("I proceed to checkout")
//    // Method to proceed with purchase until "Added to Cart"
//    public void i_proceed_to_checkout() throws Exception {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
//        addToCartButton.click();
//        Thread.sleep(2000); // Waits for 2 seconds
//        takeSnapShot(driver, "D://SFBU//Homework Screenshots");
//
//        /// Locate the "No thanks" button for the protection plan and click it
//        WebElement noThanksButton = driver.findElement(By.id("attachSiNoCoverage"));
//        noThanksButton.click();
//        Thread.sleep(3000); // Waits for 3 seconds
//        takeSnapShot(driver, "D://SFBU//Homework Screenshots");
//
//        // Click on the "Go to Cart" button
//
//        WebElement goToCartLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Go to Cart")));
//        goToCartLink.click();
//        takeSnapShot(driver, "D://SFBU//Homework Screenshots");
//
//    }
//    void takeSnapShot(WebDriver webdriver, String directoryPath) throws Exception {
//        // Convert web driver object to TakeScreenshot
//        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
//
//        // Call getScreenshotAs method to create image file
//        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
//
//        // Get current date and time
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
//        String formattedNow = now.format(formatter);
//
//        // Construct the screenshot file path using the date-time string
//        String fileWithPath = directoryPath + "//screenshot_" + formattedNow + ".png";
//
//        // Move image file to new destination
//        File DestFile = new File(fileWithPath);
//
//        // Copy file at destination
//        FileUtils.copyFile(SrcFile, DestFile);
//    }
//
//    @Then("I close the browser")
//    public void i_close_the_browser() throws Exception{
//        Thread.sleep(3000);
//        driver.quit();
//    }
//}
