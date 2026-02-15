package com.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTest {

    protected WebDriver driver;
    protected static ExtentReports extent;
    protected ExtentTest test;

    private static final String BASE_URL = "https://www.btl.gov.il/";

    private static final String REPORTS_DIR = "reports/";
    private static final String SCREENSHOTS_DIR = REPORTS_DIR + "screenshots/";

    @BeforeSuite
    public void setupReport() {
        new File(SCREENSHOTS_DIR).mkdirs();
        ExtentSparkReporter reporter = new ExtentSparkReporter(REPORTS_DIR + "extent-report.html");
        reporter.config().setDocumentTitle("BTL Automation Report");
        reporter.config().setReportName("דוח בדיקות אוטומציה - ביטוח לאומי");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        try {
            if (driver != null && test != null) {
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
                String testName = result.getMethod().getMethodName();
                String screenshotName = testName + "_" + timestamp + ".png";
                String screenshotPath = SCREENSHOTS_DIR + screenshotName;

                File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                Path destPath = Paths.get(screenshotPath);
                Files.copy(srcFile.toPath(), destPath);

                String relativePath = "screenshots/" + screenshotName;

                if (result.getStatus() == ITestResult.FAILURE) {
                    test.fail("הטסט נכשל: " + result.getThrowable().getMessage())
                        .addScreenCaptureFromPath(relativePath, screenshotName);
                } else if (result.getStatus() == ITestResult.SUCCESS) {
                    test.pass("הטסט עבר בהצלחה")
                        .addScreenCaptureFromPath(relativePath, screenshotName);
                }
            }
        } catch (Exception e) {
            if (test != null) {
                test.fail("שגיאה בצילום מסך: " + e.getMessage());
            }
        }
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void tearDownReport() {
        extent.flush();
    }
}
