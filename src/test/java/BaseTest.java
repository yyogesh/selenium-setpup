import framework.config.ConfigManager;
import framework.driver.DriverFactory;
import framework.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base class for all test classes
 */
public class BaseTest {
    protected WebDriver driver;
    protected ConfigManager configManager;

    @BeforeMethod
    public void setUp() {
        configManager = ConfigManager.getInstance();
        driver = DriverFactory.getDriver();
        driver.get(configManager.getBaseUrl());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Take screenshot on test failure
        if (result.getStatus() == ITestResult.FAILURE) {
            TestUtils.takeScreenshot(driver, result.getName());
        }

        DriverFactory.quitDriver();
    }
}