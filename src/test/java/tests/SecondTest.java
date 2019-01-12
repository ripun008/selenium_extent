package tests;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SecondTest extends BaseTest {

    @Test
    public void testFacebook() {
        //logger.log(Status.INFO, "go to facebook");

        driver.get("http://www.facebook.com");
        //logger.log(Status.PASS, "test passed");
    }

    @Test
    public void testMe() {
        Assert.assertEquals(1,1);
    }

    @Test
    public void testFailure() {
        driver.get("http://www.google.com");
        driver.findElement(By.cssSelector(".casgas")).click();
    }
}
