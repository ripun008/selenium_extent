package listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTest;
import utils.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestListener extends BaseTest implements ITestListener {

    // Extent Report Declarations
    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " started!"));
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
        test.set(extentTest);
    }

    public void onTestSuccess(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " passed!"));
        test.get().pass("Test passed");
    }

    public void onTestFailure(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " failed!"));
        try {
            getScreenshot(result.getMethod().getQualifiedName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        test.get().fail(result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        System.out.println((result.getMethod().getMethodName() + " skipped!"));
        test.get().skip(result.getThrowable());
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
    }

    public void onStart(ITestContext iTestContext) {
        System.out.println("Extent Reports Version 3 Test Suite started!");
    }

    public void onFinish(ITestContext iTestContext) {
        System.out.println(("Extent Reports Version 3  Test Suite is ending!"));
        // Flush method will write the test in report- This is mandatory step
        extent.flush();
    }

    public static void getScreenshot(String testMethodName) throws IOException {
        String fileName = testMethodName + ".png";
        String directory = System.getProperty("user.dir") +"/src/TestReport/Screenshots/";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File(directory + fileName));
        test.get().addScreenCaptureFromPath("Screenshots//" + fileName);
    }
}
