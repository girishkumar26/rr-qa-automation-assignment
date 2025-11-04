package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.DriverFactory;
import utils.ExtentManager;
import utils.LoggerHelper;
import utils.ScreenshotHelper;

import java.io.File;
import java.lang.reflect.Method;

public class BaseTest {

    protected WebDriver driver;
    protected org.apache.logging.log4j.Logger logger;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite(alwaysRun = true)
    public void startReport() {
        logger = LoggerHelper.getLogger(BaseTest.class);
        extent = ExtentManager.createInstance("reports/ExtentReport.html");
        logger.info("✅ Extent Report Initialized");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp(Method method) {
        logger = LoggerHelper.getLogger(BaseTest.class); // Initialize logger first

        try {
            driver = DriverFactory.getDriver();
            driver.manage().window().maximize();
            logger.info("Browser launched and maximized.");
            driver.get("https://tmdb-discover.surge.sh");
        } catch (Exception e) {
            logger.error("Runtime Driver setup failed. Cannot proceed with tests.", e);
            throw new RuntimeException("Driver setup failed", e);
        }

        test = extent.createTest(method.getName());
        logger.info("Test Started: " + method.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        if (logger == null) {
            logger = LoggerHelper.getLogger(BaseTest.class); // safety fallback
        }

        String testName = result.getMethod().getMethodName();

        try {
            switch (result.getStatus()) {
                case ITestResult.FAILURE:
                    logger.error("Test Failed: " + testName);
                    String screenshotPath = ScreenshotHelper.captureScreenshot(driver, testName);

                    if (screenshotPath != null) {
                        test.fail("Test Failed. Screenshot attached.").
                                addScreenCaptureFromPath(new File(screenshotPath).getAbsolutePath());
                    } else {
                        test.fail("Test Failed but screenshot could not be captured.");
                    }
                    break;

                case ITestResult.SUCCESS:
                    logger.info("Test Passed: " + testName);
                    test.pass("Test Passed");
                    break;

                case ITestResult.SKIP:
                    logger.warn("Test Skipped: " + testName);
                    test.skip("Test Skipped");
                    break;
            }
        } catch (Exception e) {
            logger.error("Error in AfterMethod: " + e.getMessage(), e);
        } finally {
            if (driver != null) {
                driver.quit();
                logger.info("Browser closed.");
            }
        }
    }

    @AfterSuite(alwaysRun = true)
    public void endReport() {
        if (extent != null) {
            extent.flush();
            logger.info("Extent Report Flushed.");
        }
    }
}
