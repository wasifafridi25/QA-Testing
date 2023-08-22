//package StepDefinition;
//
//import io.cucumber.java.PendingException;
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.junit.Assert;
//import org.junit.Test;
//import org.openqa.selenium.By;
//import org.openqa.selenium.Keys;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.Select;
//
//import java.time.Duration;
//import java.util.List;
//
//public class mySFBUJunitRunnerSteps {
//
//    WebDriver driver;
//    Actions actions;
//    String driverPath = "C:\\Drivers\\Selenium\\chrome\\chromedriver.exe";
//    static String sfbuUrl = "https://sfbu.edu";
//    static String linkCreateAccount = "Create Account";
//    static String applicantType = "new";
//    static String myFirstName = "Charles";
//    static String myLastName = "Sfbu";
//    static String myGender = "Male";
//    static String myEmail = "myEmail@student.sfbu.edu";
//    static String my1stPassword = "1234";
//    static String my2ndPassword = "5678";
//    static String mySurveyType = "NPU student/alumni";
//    static String mySurveyTypeDetails = "My Friend";
//
//    static String passwordTooShortMsg = "'Password' must be at least 8 characters long.";
////    static String passwordTooShortMsg = "I love SFBU.";
//    static String passwordConfirmMsg = "The password and confirmation password do not match.";
//    static String otherMsg = null;
//
//    @Test
//    @Given("^Open the Chrome and launch the SFBU application$")
//    public void open_the_Chrome_and_launch_the_SFBU_application() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        System.setProperty("webdriver.chrome.driver", driverPath);
//        driver = new ChromeDriver();
//        actions = new Actions(driver);
//        driver.get(sfbuUrl);
//        driver.manage().window().maximize();
////        throw new PendingException();
//    }
//
//    @Test
//    @When("^Open Application Portal$")
//    public void open_Application_Portal() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        // tab bar click Apply Today
//        driver.findElement(By.cssSelector("#block-sfbu-mainnavigationright > ul > li:nth-child(1) > a")).click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
////        throw new PendingException();
//    }
//
////    @Test
////    @When("^Enter the Username as \"([^\"]*)\" and Password \"([^\"]*)\"$")
////    public void enter_the_Username_as_and_Password(String arg1, String arg2) throws Throwable {
////        // Write code here that turns the phrase above into concrete actions
////        driver.findElement(By.name("UserName")).sendKeys(arg1);
////        driver.findElement(By.name("Password")).sendKeys(arg2);
////        System.out.println("Username is: " + arg1);
////        System.out.println("Password is: " + arg2);
////        driver.findElement(By.cssSelector("input[type='submit']")).click();
////        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//////        throw new PendingException();
////    }
//
//    @Test
////    @When("^Enter the Username as \"([^\"]*)\" and Password \"([^\"]*)\"$")
//    public void enter_the_Username_as_and_Password() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        driver.findElement(By.name("UserName")).sendKeys("user1");
//        driver.findElement(By.name("Password")).sendKeys("password1");
//        System.out.println("Username is: " + "user1");
//        System.out.println("Password is: " + "password1");
//        driver.findElement(By.cssSelector("input[type='submit']")).click();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
////        throw new PendingException();
//    }
//
////    @Test
////    @Then("^Login should be failed \"([^\"]*)\"$")
////    public void login_should_be_failed(String arg1) throws Throwable {
////        // Write code here that turns the phrase above into concrete actions
////        boolean b = driver.getPageSource().contains(arg1);
////        try {
////            Assert.assertTrue(b);
////            System.out.println("Found expected message: " + arg1 + " ----- Test Passed!");
////        } catch (Throwable e){
////            System.out.println("Not Found expected message: " + arg1 + " ----- Test Failed");
////        }
//////        throw new PendingException();
////    }
//
//    @Test
//    @Then("^Login should be failed \"([^\"]*)\"$")
//    public void login_should_be_failed() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        boolean b = driver.getPageSource().contains("message1");
//        try {
//            Assert.assertTrue(b);
//            System.out.println("Found expected message: " + "message1" + " ----- Test Passed!");
//        } catch (Throwable e){
//            System.out.println("Not Found expected message: " + "message1" + " ----- Test Failed");
//        }
////        throw new PendingException();
//    }
//
//    @Test
//    @Then("^Logout$")
//    public void logout() throws Throwable {
//        // Write code here that turns the phrase above into concrete actions
//        driver.quit();
////        throw new PendingException();
//    }
//
//}