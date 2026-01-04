@US004
@hamburgerMenu
@navigation
Feature: HamburgerMenu - Messaging Management Functionality

  As a student,
  I want to be able to manage my messages via the messages link in the Hamburger Menu (Messaging).
  This way I can use the campus application more effectively.

  Background:
    Given User is logged in as a student

  @TC0401
  @smoke
  Scenario: Verify messaging sub-menu options and navigation
    #Given the user is logged into the Campus system as a "Student"
    When the user clicks on the "Hamburger Menu" icon
    And the user hovers over the "Messaging" menu item
    Then the following sub-menu options should be visible:
      | New Message |
      | Inbox       |
      | Outbox      |
      | Trash       |
    And the user clicks on the "New Message" link
    Then the "New Message" page or pop-up should be displayed