@;eadpage @Functional
Feature: Leads Page Test

  @lead0201
  Scenario: 02.01 User create new Leads
    Given User login with account "Emil"
    Then Able to see that "Leads" tab is visible on page
    When User click on "New" button on Leads page
    Then Able to see New Lead form is visible
    When User click on "Unqualified Lead" New Lead type check box
    And User click on "Next" button in New Lead Form
    Then Able to see "Unqualified Lead" New Lead Form loaded

