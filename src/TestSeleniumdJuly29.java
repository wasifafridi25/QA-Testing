//import org.apache.log4j.Logger;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//
//public class TestSeleniumdJuly29 {
//
//    private static Logger logger = Logger.getLogger(log4jExample.class.getName());
//
//    public static void main(String[] args) throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver","C:\\Drivers\\Selenium\\chrome\\chromedriver.exe");
//
//        WebDriver driver = new ChromeDriver();
//        Actions actions = new Actions(driver);
//
//        driver.get("http://www.google.com");
//        driver.manage().window().maximize();
//        Thread.sleep(2000);
//
//        driver.findElement(By.name("q")).sendKeys("SFBU");
//        actions.sendKeys(Keys.ENTER).build().perform();
//        Thread.sleep(2000);
//
//        driver.navigate().to("https://sfbu.edu");
//        Thread.sleep(2000);
//
//        String expectedTitle = "SFBU | San Francisco Bay University | SFBU";
//
//        String actualTitle = driver.getTitle();
//        System.out.println("Current open web page title is " + actualTitle);
//        if (actualTitle.contentEquals(expectedTitle)){
//            System.out.println("Test Passed!");
//            logger.info("Hello this is an passed info message");
//        } else {
//            System.out.println("Test Failed");
//            logger.error("Hello this is an failed info message");
//        }
//
////        driver.close();
//        driver.quit();
//
//    }
//}
