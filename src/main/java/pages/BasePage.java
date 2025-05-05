package pages;


import framework.driver.DriverFactory;
import framework.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base class for all page objects
 */

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final int DEFAULT_TIMEOUT = 10;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        PageFactory.initElements(driver, this);
    }

    /**
     * Click on element with built-in wait
     *
     * @param element WebElement to click
     */
    protected void clickElement(WebElement element) {
        try {
            TestUtils.waitForElementClickable(driver, element, DEFAULT_TIMEOUT);
            element.click();
        } catch (Exception e) {
            System.err.println("Failed to click element: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Send keys to element with built-in wait
     *
     * @param element WebElement to send keys to
     * @param text Text to send
     */
    protected void sendKeys(WebElement element, String text) {
        try {
            TestUtils.waitForElementVisible(driver, element, DEFAULT_TIMEOUT);
            element.clear();
            element.sendKeys(text);
        } catch (Exception e) {
            System.err.println("Failed to send keys to element: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Get text from element with built-in wait
     *
     * @param element WebElement to get text from
     * @return Text content of the element
     */
    protected String getText(WebElement element) {
        try {
            TestUtils.waitForElementVisible(driver, element, DEFAULT_TIMEOUT);
            return element.getText();
        } catch (Exception e) {
            System.err.println("Failed to get text from element: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Check if element is displayed
     *
     * @param element WebElement to check
     * @return True if element is displayed, false otherwise
     */
    protected boolean isElementDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Wait for page to load completely
     */
    protected void waitForPageLoad() {
        wait.until(driver -> ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }
}
