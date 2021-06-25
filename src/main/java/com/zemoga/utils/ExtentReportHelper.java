package com.zemoga.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ExtentReportHelper {
    private static ExtentReports extent;
    private static ExtentSparkReporter spark;
    private static WebDriver driver;
    private static int DEFAULT_TIMEOUT = 10;

    /*public static void createReport() {
        extent = new ExtentReports();

        // creates a toggle for the given test, adds all log events under it


        // log(LogStatus, details)
        test.log(LogStatus.INFO, "This step shows usage of log(logStatus, details)");

        // report with snapshot
        String img = test.addScreenCapture("img-path");
        test.log(LogStatus.INFO, "Image", "Image example: " + img);

        // end test
        extent.endTest(test);

        // calling flush writes everything to the log file
        extent.flush();
    }*/

    public static ExtentReports getExtentReport() {
        createExtentReportIfNull();
        return extent;
    }

    private static void createExtentReportIfNull() {
        if (extent == null) {
            extent = new ExtentReports();
            spark = new ExtentSparkReporter("Test_Report.html");
            extent.attachReporter(spark);
        }
    }

    public static WebDriver getWebDriver() {
        return getWebDriver(DEFAULT_TIMEOUT);
    }

    public static WebDriver getWebDriver(int timeoutInSeconds) {
        createWebDriverIfNull(timeoutInSeconds);
        return driver;
    }

    private static void createWebDriverIfNull(int timeout) {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            String parent = driver.getWindowHandle();
            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
        }
    }

    public static void quitWebDriver() {
        driver.quit();
        driver = null;
    }
}
