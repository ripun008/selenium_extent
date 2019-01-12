package tests;

import com.aventstack.extentreports.Status;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    @Test
    public void testGoogle() {
        //logger.log(Status.INFO, "go to google");

        driver.get("http://www.google.com");
        //logger.log(Status.PASS, "test passed");
    }

    @Test
    public void testGmail() {
        //logger.log(Status.INFO, "go to gmail");

        driver.get("http://www.gmail.com");
        //logger.log(Status.PASS, "test passed");
    }

    @Test
    @Ignore
    public void testSkip() {
        driver.get("http://www.apple.com");
    }
}
