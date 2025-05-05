import org.testng.Assert;
import org.testng.annotations.Test;
import pages.InductionPlannerPage;

/**
 * Test class for Induction Planner functionality
 */
public class InductionPlannerTest extends BaseTest {

    // @Test(description = "Verify user can login to the Induction Planner")
    // public void testLogin() {
    //     InductionPlannerPage plannerPage = new InductionPlannerPage();

    //     plannerPage.login(configManager.getUsername(), configManager.getPassword());

    //     Assert.assertTrue(plannerPage.isLoginSuccessful(), "Login was not successful");

    //     String welcomeMessage = plannerPage.getWelcomeMessage();
    //     Assert.assertTrue(welcomeMessage.contains(configManager.getUsername()),
    //             "Welcome message does not contain the username");

    //     plannerPage.logout();
    // }

    @Test(description = "Verify user can create a new induction plan")
    public void testCreateInductionPlan() {
        InductionPlannerPage plannerPage = new InductionPlannerPage();

        // Login first
        // plannerPage.login(configManager.getUsername(), configManager.getPassword());
        
        // With:
        plannerPage.loginWithBoeingSso();
        Assert.assertTrue(plannerPage.isLoginSuccessful(), "Login was not successful");

        // Create a new plan
        String planTitle = "Test Plan " + System.currentTimeMillis();
        String planDescription = "This is an automated test plan created by Selenium";

        plannerPage.createPlan(planTitle, planDescription);

        Assert.assertTrue(plannerPage.isPlanCreationSuccessful(),
                "Plan creation was not successful");

        plannerPage.logout();
    }

    @Test(description = "End-to-end test for Induction Planner workflow")
    public void testInductionPlannerE2E() {
        InductionPlannerPage plannerPage = new InductionPlannerPage();

        // Step 1: Login
        plannerPage.login(configManager.getUsername(), configManager.getPassword());
        Assert.assertTrue(plannerPage.isLoginSuccessful(), "Login was not successful");

        // Step 2: Create a plan
        String planTitle = "E2E Test Plan " + System.currentTimeMillis();
        String planDescription = "This is an E2E test for the induction planner workflow";

        plannerPage.createPlan(planTitle, planDescription);
        Assert.assertTrue(plannerPage.isPlanCreationSuccessful(),
                "Plan creation was not successful");

        // Step 3: Logout
        plannerPage.logout();
    }
}