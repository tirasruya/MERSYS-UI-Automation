@US001
@login
Feature: Login Feature

  As a student
  I want to login to the system
  So that I can access courses and communicate with teachers

  Background:
    Given User is on the login page

  @TC101
  @smoke
  @positive
  Scenario: User logs in successfully with valid student credentials
    When User enters username "Student_10" and password "S12345"
    And User clicks on login button
    Then User should be redirected to the homepage

  @TC102
  @negative
  Scenario Outline: User cannot log in with invalid or missing credentials
    When User enters username "<username>" and password "<password>"
    And User clicks on login button
    Then User should see error message "<expectedError>" for "<errorType>"

    Examples:
      | username   | password | expectedError                | errorType |
      | wrongUser  | S12345   | Invalid username or password | login     |
      | Student_10 | wrongPwd | Invalid username or password | login     |
      | wrongUser  | wrongPwd | Invalid username or password | login     |
      |            | S12345   | E-mail is required           | username  |
      | Student_10 |          | Password is required         | password  |