package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.Platform;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extent;
    private static ExtentHtmlReporter htmlReporter;
    private static Platform platform;

    private static String reportPath = getReportPath();
    private static String screenshotsPath = getScreenshotsPath();

    public static ExtentReports getInstance() {
        if (extent == null) {
            clearResults();
            createInstance();
        }

        return extent;
    }

    // Create an extent report instance
    public static ExtentReports createInstance() {
        // Set up Extent report
        htmlReporter = new ExtentHtmlReporter(reportPath);
        htmlReporter.setAppendExisting(true);

        // initialize ExtentReports and attach the HtmlReporter
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }

    private static Platform getPlatform() {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win")) {
            platform = Platform.WINDOWS;
        } else if(os.contains("mac")) {
            platform = Platform.MAC;
        }
        return platform;
    }

    private static String getReportPath() {
        String path = null;
        platform = getPlatform();
        switch (platform) {
            case MAC:
                path = System.getProperty("user.dir")+ "//src//TestReport//results.html";
                break;

            case WINDOWS:
                path = System.getProperty("user.dir")+ "\\src\\TestReport\\results.html";
                break;

                default:
                    path = System.getProperty("user.dir")+ "//src//TestReport//results.html";
                    break;
        }
        return path;
    }

    private static String getScreenshotsPath() {
        String path = null;
        platform = getPlatform();
        switch (platform) {
            case MAC:
                path = System.getProperty("user.dir")+ "//src//TestReport//Screenshots";
                break;

            case WINDOWS:
                path = System.getProperty("user.dir")+ "\\src\\TestReport\\Screenshots";
                break;

            default:
                path = System.getProperty("user.dir")+ "//src//TestReport//Screenshots";
                break;
        }
        return path;
    }

    private static void clearResults() {
        File resultsFile = new File(reportPath);
        File screenShotsFile = new File(screenshotsPath);
        if(resultsFile.exists()) {
            System.out.println("Deleting old results.html file.\n");
            resultsFile.delete();
        }
//        if(screenShotsFile.exists()) {
//            System.out.println("Deleting old screenshots.\n");
//            screenShotsFile.delete();
//        }
    }
}
