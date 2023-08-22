import org.testng.Assert;
import org.testng.annotations.*;

public class annotationsTest2 {
    @BeforeTest
    public static void Beginning(){
        System.out.println("*** Beginning ***");
    }

    @BeforeClass
    public void OpenBrowser(){
        System.out.println("Open browser");
    }

    @AfterTest
    public static void Ending(){
        System.out.println("*** Ending ***");
    }

    @AfterClass
    public void CloseBrowser(){
        System.out.println("Close browser");
    }

    @Test
    public void SendGoogleEmailTest(){
        System.out.println("Testing Sending Google Email");
        int SendingOutGoogleEmail = 11;
        int ReceivingGoogleEmail = 10;
        Assert.assertEquals(SendingOutGoogleEmail, ReceivingGoogleEmail);
//        try{
//            Assert.assertEquals(SendingOutGoogleEmail, ReceivingGoogleEmail);
//        }catch(Throwable t){
//            System.out.print("Error");
//        }
        System.out.println("Sending Google Email != Receiving Google Email");
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
