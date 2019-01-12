package tests;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    public static WebDriver driver;
    public WebDriverWait wait;


    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void setup () {

        // Create a Chrome driver. All test classes use this.
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win")) {
            System.setProperty("webdriver.chrome.driver", ".//src//bin//chromedriver.exe");
        } else if(os.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", ".//src//bin//chromedriver");
        }
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("start-maximized");
        options.addArguments("disable-infobars");

        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        //Create a wait. All test classes use this.
        wait = new WebDriverWait(driver,15);
    }

    @AfterClass
    public void teardown () {
        // Quit chromedriver
        driver.quit();
    }

}
