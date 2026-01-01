package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.AssignmentsPage;
import pages.HomePage;
import utils.BaseDriver;

public class Assignments_AssignedTasksSteps {

    WebDriver driver;
    HomePage homePage;
    AssignmentsPage assignmentsPage;

    @Then("User should see Assignments logo on the home page")
    public void userShouldSeeAssignmentsLogo() {
        driver = BaseDriver.getDriver();
        homePage = new HomePage(driver);
        assignmentsPage = new AssignmentsPage(driver);

        assignmentsPage.verifyAssignmentsLogoDisplayed();
    }

    @When("User hovers over Assignments logo")
    public void userHoversOverAssignmentsLogo() {
        assignmentsPage.hoverOverAssignmentsLogo();
    }

    @Then("User should see total number of assigned tasks")
    public void userShouldSeeTotalNumberOfAssignedTasks() {
        assignmentsPage.verifyAssignmentsCountOnHover();
    }

    @When("User clicks on Assignments logo")
    public void userClicksOnAssignmentsLogo() {
        assignmentsPage.clickAssignmentsLogo();
    }

    @Then("User should see all assigned tasks on Assignments page")
    public void userShouldSeeAllAssignedTasks() {
        assignmentsPage.verifyAssignedTasksDisplayed();
    }
}