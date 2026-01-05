@US005
@hamburgerMenu @Messaging

Feature: US005 Messaging - Message Creation, Sending, and Outbox Control

  As a student, I would like to be able to send messages from the Hamburger Menu.
  This way, I can message my teachers and mentors directly through Campus.


  Background:
    Given User is logged in as a student

@TC0501
@smoke
  Scenario Outline: Message sending and outbox verification for different receiver types (<receiverType>).
    When the user clicks on the "Hamburger Menu" icon
    And the user hovers over the "Messaging" menu item
    And the user clicks on the "Send Message" link
    Then a text editor pop-up should be displayed
    And the user should see and click the "Add Receiver(s)" icon
    When the user types "<searchKey>" in the receiver field
    Then the system should display the results for "<receiverType>"
    And the user selects a random available receiver
    And the user enters a subject as "<subject>"
    And the user types "Otomasyon testi ile gönderilen mesaj içeriği." in the text editor
    And the user clicks on the "Send" button
    Then a "Success" notification should be displayed

    # Doğrulama Adımı: Outbox Kontrolü
    When the user clicks on the "Hamburger Menu" icon
    And the user clicks on the "Messaging" link and then "Outbox" link
    Then the message with subject "<subject>" should be visible in the Outbox list

    Examples:
      | receiverType | searchKey | subject                |
      | Teacher      | Tea       | Project Report v1      |
      | Student      | Stu       | Daily MSG Information  |

