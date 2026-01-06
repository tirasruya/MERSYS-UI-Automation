@US006
@hamburgerMenu @Messaging

Feature: US006 Messaging - Outbox Management Functionality

  As a student,
  I should be able to delete messages from the Hamburger Menu's outbox.
  This way, I can edit my messages when needed.


  Background:
    Given User is logged in as a student

  @TC0601
  @smoke
  Scenario: Moving an outgoing message to Trash
    When the user clicks on the "Hamburger Menu" icon
    And the user navigates to Outbox under Messaging menu
    And the user clicks on the "rubbish" icon for an outgoing message
    Then an outbox confirmation pop-up should be displayed
    When the user confirms the outbox deletion
    Then the "Success" message should be displayed indicating the message was deleted
    And the message should no longer be visible in the Outbox list