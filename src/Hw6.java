import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Hw6 {
    public static void main(String[] args) throws InterruptedException{
        //sets the system property for the Chrome WebDriver. It points to the location of the ChromeDriver executable file, which is required by Selenium to interact with the Chrome browser.
        System.setProperty("webdriver.chrome.driver","C:\\Drivers\\Selenium\\Chrome\\chromedriver.exe");

        //setting the chrome driver. Initializes a new instance of the ChromeDriver, which will be used to control the Chrome browser.
        WebDriver driver = new ChromeDriver();

        //initializes a new Actions object, which is used to perform more complex user interactions like mouse and keyboard events.
        Actions actions = new Actions(driver);

        //Instructs the WebDriver to navigate to the specified URL, which in this case is Google's homepage.
        //driver.get to open the browser
        driver.get("http://www.google.com");

        //maximizes the browser window. Ensures that all elements on the page are visible.
        driver.manage().window().maximize();

        // pauses the execution of the script for the specified amount of time (2000 milliseconds), allowing time for elements to load on the page.
        Thread.sleep(2000);

        //finds the search box on Google's homepage (which has the name "q") and types "SFBU" into it.
        driver.findElement(By.name("q")).sendKeys("SFBU"); //You just typed here didn't search yet'

        //simulates pressing the Enter key. In this context, it is used to submit the Google search.
        actions.sendKeys(Keys.ENTER).build().perform(); // now we search that's why we have actions, to hit enter key
        Thread.sleep(2000);

        //instructs the WebDriver to navigate to the specified URL.
        driver.navigate().to("https://sfbu.edu");  // no more get, cause we already opened window
        Thread.sleep(2000);

        //define a string for the expected title of the webpage
        String expectedTitle = "SFBU | San Francisco Bay University | SFBU";

        //retrieve the actual title of the current webpage.
        String actualTitle = driver.getTitle();

        //prints the title of the current webpage
        System.out.println("Current open web page title is " + actualTitle);

        //checks if the actual title of the webpage matches the expected title.
        // If they match, it prints "Test Passed!" If they don't match, it prints "Test Failed".
        if (actualTitle.contentEquals(expectedTitle)){
            System.out.println("Test Passed!");
        } else {
            System.out.println("Test Failed");
        }

        // Closes the current browser window. If it's the only open window, the WebDriver session also ends.
        driver.close();

        //Ends the entire WebDriver session and closes all associated browser windows.
        driver.quit();

    }
}
