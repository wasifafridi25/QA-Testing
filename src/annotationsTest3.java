import org.testng.Assert;
import org.testng.annotations.*;

public class annotationsTest3 {
    @BeforeTest
    public static void Begining(){
        System.out.println("Starting");
    }

    @BeforeClass
    public void OpenBrowser(){
        System.out.println("Open browser");
    }

    @AfterTest
    public static void Ending(){
        System.out.println("Ending");
    }

    @AfterClass
    public void CloseBrowser(){
        System.out.println("Close browser");
    }

    @Test
    public void SendYahooEmailTest(){
        System.out.println("Testing Sending Yahoo Email");
        int SendingOutYahooEmail = 11;
        int ReceivingYahooEmail = 11;
        try{
            Assert.assertEquals(SendingOutYahooEmail, ReceivingYahooEmail);
        }catch(Throwable t) {
            System.out.println("Error encountered");
        }
        System.out.println("Finished to test Sending Yahoo Email");
    }

    @Test
    public void OnlineShoppingTest(){
        System.out.println("Testing Online Shopping");
    }

    @Ignore
    @Test
    public void RegisterAccountTest(){
        System.out.println("Testing Register Account");
    }


}
