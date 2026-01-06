@US015
@profileFeature
@Theme
Feature: Profile Theme Settings

  As a student,
  I should be able to use at least three different color options on my profile.
  This way, I can have a more personalized user experience.

  Background:
    Given User is logged in as a student

  @TC01501
  @Regression
  Scenario: Verify that the student can change the application theme
    Given the user navigates to "Profile" > "Settings"
    Then the user should see a drop-down theme menu on the settings page
    When the user clicks on the theme drop-down menu
    Then the user should see at least three different theme options
    When the user selects a new theme from the options
    Then the user should see that the theme changes immediately without confirmation
    And the user see "Save" button on the page and click it
    Then the "Success" message should be displayed