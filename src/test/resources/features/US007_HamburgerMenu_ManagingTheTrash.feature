@US007
@hamburgerMenu
@Messaging
Feature: HamburgerMenu - Trash Management Functionality

  As a student,
  I should be able to recover (or permanently delete) messages that I've deleted (moved to the trash).
  This way, I can edit my messages when needed.

  Background:
    Given User is logged in as a student
    And the user clicks on the "Hamburger Menu" icon


  @TC0701
  @smoke
  Scenario: Recovering a deleted message from Trash
    When the user navigates to "Trash" under "Messaging"
    And the user clicks on the "Restore" icon for a deleted message
    Then the message should be removed from the list successfully
    And the message should no longer be visible in the Trash list

  @TC0702
  Scenario: Permanently deleting a message with confirmation
    When the user navigates to "Trash" under "Messaging"
    And the user clicks on the "Trash Can" icon to permanently delete a message
    Then a confirmation pop-up should be displayed with a message
    When the user confirms the permanent deletion
    Then the message should be removed from the list successfully
    And the message should be completely removed from the system