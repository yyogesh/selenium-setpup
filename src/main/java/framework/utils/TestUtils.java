package framework.utils;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility methods for test automation
 */

public class TestUtils {

    /**
     * Waits for an element to be clickable
     *
     * @param driver WebDriver instance
     * @param element Element to wait for
     * @param timeoutInSeconds Maximum time to wait
     * @return The WebElement once it's clickable
     */
    public static WebElement waitForElementClickable(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Waits for an element to be visible
     *
     * @param driver WebDriver instance
     * @param element Element to wait for
     * @param timeoutInSeconds Maximum time to wait
     * @return The WebElement once it's visible
     */
    public static WebElement waitForElementVisible(WebDriver driver, WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Scrolls to the specified element
     *
     * @param driver WebDriver instance
     * @param element Element to scroll to
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Highlights an element temporarily (useful for debugging)
     *
     * @param driver WebDriver instance
     * @param element Element to highlight
     */
    public static void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = element.getAttribute("style");

        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                element, "border: 2px solid red; background-color: yellow;");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
                element, originalStyle);
    }

    /**
     * Takes a screenshot of the current page
     *
     * @param driver WebDriver instance
     * @param testName Name of the test case
     */
    public static void takeScreenshot(WebDriver driver, String testName) {
        // Simple implementation without using third-party libraries
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("console.log('Taking screenshot for test: " + testName + "')");

        // In a real implementation, you'd save the screenshot to a file
        // org.openqa.selenium.TakesScreenshot functionality could be used
    }
}
