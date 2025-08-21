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

    When User update customer information in New Lead Form
    And User update "Vehicle Interest" search field with "SEAT Tarraco"
    And User update "Dealer" search field with "Test ISA"
    And User click on "Save" button in New Lead Form

    Then Verify new Leads record created with correct data on Lead listing page