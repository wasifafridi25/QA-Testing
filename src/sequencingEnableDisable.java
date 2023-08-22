import org.testng.annotations.Test;

public class sequencingEnableDisable {
    @Test(priority=6, enabled = true)
    public void c_method(){
        System.out.println("I'm in method C");
    }
    @Test(priority=9)
    public void b_method(){
        System.out.println("I'm in method B");
    }
    @Test(priority=6, enabled = false)
    public void a_method(){
        System.out.println("I'm in method A");
    }
    @Test(priority=0)
    public void e_method(){
        System.out.println("I'm in method E");
    }
    @Test(priority=3)
    public void d_method(){
        System.out.println("I'm in method D");
    }
}
