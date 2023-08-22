import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

public class SFBULoginDataDrivenXlsxTest2 {
    WebDriver driver;
    Actions actions;
    String driverPath = "C:\\Drivers\\Selenium\\chrome\\chromedriver.exe";
    static String sfbuUrl = "https://sfbu.edu";
    static String myLoginEmail = "1234";
    static String myLoginPassword = "5678";
    static String passwordConfirmMsg = "The password and confirmation password do not match.";
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    //private static XSSFRow Row;
    private String sTestCaseName;
    private int iTestCaseRow;

    @BeforeTest
    public void open_sfbu_application_portal() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        actions = new Actions(driver);

        driver.get(sfbuUrl);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.cssSelector("#block-sfbu-mainnavigationright > ul > li:nth-child(1) > a")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterTest
    public void logout() {
        driver.quit();
    }

    // @Test
    @Test(dataProvider = "Authentication")
    public void testSFBULoginByDataDrivenXLSX(String username, String password) {
        enterTheUsernameAsAndPassword(username, password);
        login_should_be_failed(passwordConfirmMsg);
    }

    public void enterTheUsernameAsAndPassword(String username, String password) {
        driver.findElement(By.name("UserName")).sendKeys(username);
        driver.findElement(By.name("Password")).sendKeys(password);
        System.out.println("Username is: " + username);
        System.out.println("Password is: " + password);
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void login_should_be_failed(String message) {
        boolean b = driver.getPageSource().contains(message);
        try {
            Assert.assertTrue(b);
            System.out.println("Found expected message: " + message + " ----- Test Passed!");
        } catch (Throwable e) {
            System.out.println("Not Found expected message: " + message + " ----- Test Failed");
        }
    }

    // ----------- Xlsx data provider ------------ //
    @DataProvider(name = "Authentication")
    public Object[][] Authentication() throws Exception {
        setExcelFile("C://Users//User//IdeaProjects//Trial//LoginData.xlsx", "Sheet1");
        sTestCaseName = this.toString();
        // From above method we get long test case name including package and class name etc.
        // The below method will refine your test case name, exactly the name use have used
        sTestCaseName = getTestCaseName(this.toString());
        // Fetching the Test Case row number from the Test Data Sheet
        // Getting the Test Case name to get the TestCase row from the Test Data Excel sheet
        iTestCaseRow = getRowContains(sTestCaseName, 0);
        Object[][] testObjArray = getTableArray("C://Users//User//IdeaProjects//Trial//LoginData.xlsx", "Sheet1", iTestCaseRow);
        return (testObjArray);
    }

    public static void setExcelFile(String Path, String SheetName) throws Exception {
        try {

            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(Path);

            // Access the required test Excel Work book
            ExcelWBook = new XSSFWorkbook(ExcelFile);

            // Access the required test Excel data sheet
            ExcelWSheet = ExcelWBook.getSheet(SheetName);

        } catch (Exception e) {
            throw (e);
        }
    }

    public static Object[][] getTableArray(String FilePath, String SheetName, int iTestCaseRow) throws Exception {
        String[][] tabArray = null;

        try {
            FileInputStream ExcelFile = new FileInputStream(FilePath);

            // Access the required test data sheet
            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(SheetName);
            int startRow = 1;
            int startCol = 1;
            int ci, cj;
            int totalRows = ExcelWSheet.getLastRowNum();

            // you can write a function as well to get Column count
            int totalCols = 2;
            tabArray = new String[totalRows][totalCols];
            ci = 0;
            for (int i = startRow; i <= totalRows; i++, ci++) {
                cj = 0;
                for (int j = startCol; j <= totalCols; j++, cj++) {
                    tabArray[ci][cj] = getCellData(iTestCaseRow, j);
                    System.out.println(tabArray[ci][cj]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return (tabArray);
    }

/*	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try{
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			int dataType = Cell.getCellType();
			if(dataType == 3) {
				return "";
			}
			else{
				String CellData = Cell.getStringCellValue();
				return CellData;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			throw (e);
		}
	}*/

    public static String getCellData(int RowNum, int ColNum) throws Exception {
        try {
            Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
            String CellData = Cell.getStringCellValue();
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    public static String getTestCaseName(String sTestCase) throws Exception {
        String value = sTestCase;
        try {
            int posi = value.indexOf("@");
            value = value.substring(0, posi);
            posi = value.lastIndexOf(".");
            value = value.substring(posi + 1);
            return value;
        } catch (Exception e) {
            throw (e);
        }
    }

    public static int getRowContains(String sTestCaseName, int colNum) throws Exception {
        int i;
        try {
            int rowCount = getRowUsed();
            for (i = 0; i < rowCount; i++) {
                if (getCellData(i, colNum).equalsIgnoreCase(sTestCaseName)) {
                    break;
                }
            }
            return i;
        } catch (Exception e) {
            throw (e);
        }
    }

    public static int getRowUsed() throws Exception {
        try {
            int RowCount = ExcelWSheet.getLastRowNum();
            return RowCount;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw (e);
        }
    }

}