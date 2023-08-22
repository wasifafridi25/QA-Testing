import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class SFBUCreateAccountTest {
    // ------ Global variable definitions and declarations ------ //
    WebDriver driver;
    Actions actions;
    String myWebBrowserDriver = "webdriver.chrome.driver";
    String driverPath = "C:\\Drivers\\Selenium\\chrome\\chromedriver.exe";
    static String sfbuUrl = "https://sfbu.edu";
    static String linkCreateAccount = "Create Account";
    static String applicantType = "new";
    static String myFirstName = "Charles";
    static String myLastName = "Sfbu";
    static String myGender = "Male";
    static String myEmail = "myEmail@student.sfbu.edu";
    static String my1stPassword = "1234";
    static String my2ndPassword = "5678";
    static String mySurveyType = "NPU student/alumni";
    static String mySurveyTypeDetails = "My Friend";
    static String passwordTooShortMsg = "'Password' must be at least 8 characters long.";
    static String passwordConfirmMsg = "The password and confirmation password do not match.";
    static String otherMsg = null;

    // ------ Functional Methods/Test Steps -------- //
    void setup(String url){
        System.setProperty(myWebBrowserDriver, driverPath);
        driver = new ChromeDriver();
        actions = new Actions(driver);
        driver.get(url);
        driver.manage().window().maximize();
    }
    void tearDown(){
        driver.quit();
    }
    void openApplicantPortal(String link) throws InterruptedException {
        // tab bar click Apply Today
        driver.findElement(By.cssSelector("#block-sfbu-mainnavigationright > ul > li:nth-child(1) > a")).click();
        Thread.sleep(2000);

        // click linkText Create Account
        driver.findElement(By.linkText(link)).click();
        Thread.sleep(2000);
    }
    void selectApplicant(String type){
        List<WebElement> appType = driver.findElements(By.name("userType"));
        int size = appType.size();
        for(int i = 0; i < size; i++){
            String getValue = appType.get(i).getAttribute("value");
            if(getValue.equalsIgnoreCase(type)){
                appType.get(i).click();
                break;
            }
        }

        // or
//        for (WebElement webElement : appType) {
//            String getValue = webElement.getAttribute("value");
//            if (getValue.equalsIgnoreCase(type)) {
//                webElement.click();
//                break;
//            }
//        }
    }
    void enterNames (String firstName, String lastName) {
        // Enter Given Name/First Name
        for(int i = 0; i < 3; i++){
            actions.sendKeys(Keys.TAB).build().perform();
        }
        actions.sendKeys(firstName);
        actions.build().perform();

        // Enter Surname/Primary Name
        driver.findElement(By.id("LastName")).sendKeys(lastName);
    }
    void selectGender(String gender) throws InterruptedException {
        // use original
        WebElement select = driver.findElement(By.name("Gender"));
        List<WebElement> options = select.findElements(By.tagName("option"));
        for(WebElement option: options){
            if(option.getText().equals("Nonbinary")){
                option.click();
                break;
            }
        }
        Thread.sleep(1000);
        // or
        new Select(driver.findElement(By.name("Gender"))).selectByVisibleText(gender);
        Thread.sleep(1000);
    }
    void enterEmail(String email) {
        driver.findElement(By.id("Email")).sendKeys(email);
    }
    void enterPasswords(String password1st, String password2nd) {
        // Enter an invalid (not qualified) password
        driver.findElement(By.id("Password")).sendKeys(password1st);

        // Enter Confirm password
        driver.findElement(By.id("ConfirmPassword")).sendKeys(password2nd);
    }
    void selectSurvey(String type, String details) {
        switch(type) {
            case "Internet search":
                driver.findElement(By.id("survey_0_0")).click();
                driver.findElement(By.id("userInput_survey_0_0")).sendKeys(details);
                break;
            case "NPU student/alumni":
                driver.findElement(By.id("survey_0_3")).click();
                driver.findElement(By.id("userInput_survey_0_3")).sendKeys(details);
                break;
            case "Community event":
                driver.findElement(By.id("survey_0_5")).click();
                driver.findElement(By.id("userInput_survey_0_5")).sendKeys(details);
                break;
            default:
                driver.findElement(By.id("survey_0_8")).click();
                driver.findElement(By.id("userInput_survey_0_8")).sendKeys(details);
        }
    }
    void save() throws InterruptedException {
        // click Button Save
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }
    void checkMessages(String msg1, String msg2, String msg3) throws InterruptedException {
        if (msg1 != null) {
            boolean b = driver.getPageSource().contains(msg1);
            if (b){
                System.out.println("Found expected message: " + msg1 + " ----- Test Passed!");
            } else {
                System.out.println("Not Found expected message: " + msg1 + " ----- Test Failed");
            }
        }
        if (msg2 != null) {
            boolean b = driver.getPageSource().contains(msg2);
            if (b){
                System.out.println("Found expected message: " + msg2 + " ----- Test Passed!");
            } else {
                System.out.println("Not Found expected message: " + msg2 + " ----- Test Failed");
            }
        }
        if (msg3 != null) {
            boolean b = driver.getPageSource().contains(msg3);
            if (b){
                System.out.println("Found expected message: " + msg3 + " ----- Test Passed!");
            } else {
                System.out.println("Not Found expected message: " + msg3 + " ----- Test Failed");
            }
        }
        Thread.sleep(2000);
    }

    // ---------- Test Cases execution ------------ //
    public static void main(String[] args) throws InterruptedException {
        SFBUCreateAccountTest obj = new SFBUCreateAccountTest();

        obj.setup(sfbuUrl);
        obj.openApplicantPortal(linkCreateAccount);
        obj.selectApplicant(applicantType);
        obj.enterNames(myFirstName, myLastName);
        obj.selectGender(myGender);
        obj.enterEmail(myEmail);
        obj.enterPasswords(my1stPassword, my2ndPassword);
        obj.selectSurvey(mySurveyType, mySurveyTypeDetails);
//        obj.save();
        obj.checkMessages(passwordTooShortMsg, passwordConfirmMsg, otherMsg);
        obj.tearDown();
    }

}
