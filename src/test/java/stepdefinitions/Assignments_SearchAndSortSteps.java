package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.AssignmentsPage;
import utils.BaseDriver;


public class Assignments_SearchAndSortSteps {

    WebDriver driver = BaseDriver.getDriver();
    AssignmentsPage assignmentsPage = new AssignmentsPage(driver);

    @Then("User should see search button")
    public void userShouldSeeSearchButton() {
        Assert.assertTrue(assignmentsPage.isSearchButtonDisplayed());
    }

    @Then("User should see filter options")
    public void userShouldSeeFilterOptions() {
        Assert.assertTrue(assignmentsPage.areFiltersDisplayed());
    }

    @Then("User should see sort dropdown")
    public void userShouldSeeSortDropdown() {
        Assert.assertTrue(assignmentsPage.isSortDropdownDisplayed());
    }

    @When("User clicks search without applying any filter")
    public void userClicksSearchWithoutApplyingAnyFilter() {
        assignmentsPage.clickSearchButton();
    }

    @Then("All assigned tasks should be listed")
    public void allAssignedTasksShouldBeListed() {
        assignmentsPage.verifyAssignedTasksDisplayed();
    }

    @When("User filters assignments by course")
    public void userFiltersAssignmentsByCourse() {
        assignmentsPage.filterByCourse();
    }

    @When("User filters assignments by status")
    public void userFiltersAssignmentsByStatus() {
        assignmentsPage.filterByStatus();
    }

    @When("User filters assignments by semester")
    public void userFiltersAssignmentsBySemester() {
        assignmentsPage.filterBySemester();
        assignmentsPage.clickSearchButton();
    }

    @Then("Filtered assignments should be listed")
    public void filteredAssignmentsShouldBeListed() {
        assignmentsPage.verifyAssignedTasksDisplayed();
    }

    @When("User opens sort dropdown")
    public void userOpensSortDropdown() {
        assignmentsPage.openSortDropdown();
    }

    @When("User sorts assignments by course")
    public void userSortsAssignmentsByCourse() {
        assignmentsPage.sortByCourse();
    }

    @Then("Assignments should be sorted accordingly")
    public void assignmentsShouldBeSortedAccordingly() {
        assignmentsPage.verifyAssignedTasksDisplayed();
    }
}