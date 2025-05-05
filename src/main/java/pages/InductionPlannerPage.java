package pages;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Page object for the Induction Planner application
 */
public class InductionPlannerPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "loginButton")
    private WebElement loginButton;
    
    @FindBy(xpath = "//button[contains(text(), 'Boeing SSO (preferred)')]") 
    private WebElement boeingSsoButton;
    
    @FindBy(xpath = "//button[text()='Read Badge and Sign On']")
    private WebElement readBadgeAndSignOnButton;
    
    @FindBy(xpath = "//button[text()='OK']")
    private WebElement okButton;
    
    @FindBy(id = "welcomeMessage")
    private WebElement welcomeMessage;

    @FindBy(id = "createPlanBtn")
    private WebElement createPlanButton;

    @FindBy(id = "planTitle")
    private WebElement planTitleField;

    @FindBy(id = "planDescription")
    private WebElement planDescriptionField;

    @FindBy(id = "savePlanBtn")
    private WebElement savePlanButton;

    @FindBy(id = "successMessage")
    private WebElement successMessage;

    @FindBy(id = "logoutBtn")
    private WebElement logoutButton;

    /**
     * Login to the application
     *
     * @param username Username for login
     * @param password Password for login
     * @return InductionPlannerPage instance for method chaining
     */
    public InductionPlannerPage login(String username, String password) {
        sendKeys(usernameField, username);
        sendKeys(passwordField, password);
        clickElement(loginButton);
        waitForPageLoad();
        return this;
    }
    
    /**
     * Login using Boeing SSO authentication
     * 
     * @return InductionPlannerPage instance for method chaining
     */
    public InductionPlannerPage loginWithBoeingSso() {
        // Step 1: Click on Boeing SSO button
        clickElement(boeingSsoButton);
        waitForPageLoad();
        
        // Step 2: Click on Read Badge and Sign On button
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        longWait.until(ExpectedConditions.elementToBeClickable(readBadgeAndSignOnButton));
        clickElement(readBadgeAndSignOnButton);
        
        // Step 3: Handle certificate dialog with Robot class to press Enter
        try {
            // Wait for the dialog to appear
            Thread.sleep(3000); // Give time for dialog to appear
            
            // Use Robot to press Enter to click the OK button
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            
            // Wait for authentication to complete and welcome message to appear
            longWait.until(ExpectedConditions.visibilityOf(welcomeMessage));
        } catch (Exception e) {
            System.err.println("SSO authentication may not have completed: " + e.getMessage());
        }
        
        return this;
    }

    /**
     * Verify that login was successful
     *
     * @return True if welcome message is displayed, false otherwise
     */
    public boolean isLoginSuccessful() {
        return isElementDisplayed(welcomeMessage);
    }

    /**
     * Create a new induction plan
     *
     * @param title Title of the plan
     * @param description Description of the plan
     * @return InductionPlannerPage instance for method chaining
     */
    public InductionPlannerPage createPlan(String title, String description) {
        clickElement(createPlanButton);
        sendKeys(planTitleField, title);
        sendKeys(planDescriptionField, description);
        clickElement(savePlanButton);
        waitForPageLoad();
        return this;
    }

    /**
     * Verify that plan creation was successful
     *
     * @return True if success message is displayed, false otherwise
     */
    public boolean isPlanCreationSuccessful() {
        return isElementDisplayed(successMessage);
    }

    /**
     * Get the welcome message text
     *
     * @return Welcome message text
     */
    public String getWelcomeMessage() {
        return getText(welcomeMessage);
    }

    /**
     * Logout from the application
     */
    public void logout() {
        clickElement(logoutButton);
        waitForPageLoad();
    }
}