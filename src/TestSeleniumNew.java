import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.function.Function;

public class TestSeleniumNew {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Drivers\\Selenium\\chrome\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        Actions actions = new Actions(driver);

        driver.get("http://www.google.com");
        driver.manage().window().maximize();

        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));

        driver.findElement(By.name("q")).sendKeys("SFBU");
        actions.sendKeys(Keys.ENTER).build().perform();

        // implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));

        driver.navigate().to("https://sfbu.edu");

        // Explicit wait
        WebElement res = new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions
                .elementToBeClickable(By.cssSelector("#main-container > div > section.paragraph.imagebg.cover.paragraph--type--paragraph-header-block.imagebg.height-100.lcp.paragraph--view-mode--default > div.container.pos-vertical-center > div > div > div > a:nth-child(3)")));

        // fluent wait
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);
        WebElement we = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.cssSelector("#block-sfbu-mainnavigationright > ul > li:nth-child(4) > a > span"));
            }
        });

        String expectedTitle = "SFBU | San Francisco Bay University | SFBU";
        String actualTitle = driver.getTitle();
        System.out.println("Current open web page title is " + actualTitle);
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

        driver.close();
        driver.quit();
    }
}