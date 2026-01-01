@US022
@assignments
@search
Feature: Search and sort assignments

  As a student
  I want to search and sort my assigned tasks
  So that I can access information easily

  Background:
    Given User is logged in as a student
    And User is on the Assignments page

  @TC2201
  @positive
  Scenario: Student should see search area on Assignments page
    Then User should see search button
    And User should see sort dropdown
    And User should see filter options

  @TC2202
  @positive
  Scenario: Default search should list all assignments
    When User clicks search without applying any filter
    Then All assigned tasks should be listed

  @TC2203
  @positive
  Scenario: Student should filter assignments
    When User filters assignments by course
    And User filters assignments by status
    And User filters assignments by semester
    Then Filtered assignments should be listed

  @TC2204
  @positive
  Scenario: Student should sort assignments
    When User opens sort dropdown
    And User sorts assignments by course
    Then Assignments should be sorted accordingly