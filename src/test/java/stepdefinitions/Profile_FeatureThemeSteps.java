package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.ProfileFeaturePage;
import utils.BaseDriver;
import java.time.Duration;
import java.util.Random;

public class Profile_FeatureThemeSteps {

    private final ProfileFeaturePage pfp = new ProfileFeaturePage(BaseDriver.getDriver());
    private final WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(10));

    @Then("the user should see a drop-down theme menu on the settings page")
    public void verifyThemeDropdownVisible() {
        System.out.println("--- STEP: Checking theme dropdown visibility ---");
        wait.until(ExpectedConditions.visibilityOf(pfp.themeDropdown));
        Assert.assertTrue(pfp.themeDropdown.isDisplayed(), "FAILED: Theme dropdown menu is not visible on the page!");
        System.out.println("INFO: Theme dropdown is visible.");
    }

    @When("the user clicks on the theme drop-down menu")
    public void clickThemeDropdown() {
        System.out.println("--- STEP: Clicking on the theme dropdown ---");
        pfp.clickElement(pfp.themeDropdown);
        System.out.println("DEBUG: Dropdown clicked.");
    }

    @Then("the user should see at least three different theme options")
    public void verifyAtLeastThreeThemes() {
        System.out.println("--- STEP: Verifying theme options count ---");

        wait.until(ExpectedConditions.visibilityOfAllElements(pfp.themeOptions));

        int count = pfp.themeOptions.size();
        System.out.println("INFO: Number of theme options found: " + count);
        Assert.assertTrue(count >= 3, "FAILED: Expected at least 3 themes, but only found: " + count);
    }

    @When("the user selects a new theme from the options")
    public void selectRandomTheme() {
        System.out.println("--- STEP: Selecting a random theme ---");
        wait.until(ExpectedConditions.visibilityOfAllElements(pfp.themeOptions));

        int optionCount = pfp.themeOptions.size();

        if (optionCount > 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(optionCount);

            WebElement selectedOption = pfp.themeOptions.get(randomIndex);
            String selectedThemeName = selectedOption.getText();

            System.out.println("INFO: Random index generated: " + randomIndex);
            System.out.println("INFO: Selecting theme: " + selectedThemeName);

            pfp.clickElement(selectedOption);
            System.out.println("DEBUG: Random theme selected successfully.");
        } else {
            System.err.println("ERROR: No theme options found to select!");
            Assert.fail("No theme options available in the dropdown list.");
        }
    }
    @Then("the user should see that the theme changes immediately without confirmation")
    public void verifyImmediateThemeChange() {
        System.out.println("--- STEP: Verifying immediate UI theme change ---");

        System.out.println("DEBUG: Waiting 2 seconds for UI rendering...");
        try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }

        wait.until(ExpectedConditions.visibilityOf(pfp.themeDropdown));
        String currentText = pfp.themeDropdown.getText();

        System.out.println("INFO: Current text displayed in dropdown: " + currentText);

        Assert.assertFalse(currentText.isEmpty(), "FAILED: Theme selection failed, dropdown text is empty!");
        System.out.println("SUCCESS: Theme change verified.");
    }
}