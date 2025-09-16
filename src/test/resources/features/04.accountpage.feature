@accountpage @Functional
Feature: Account Page Test

  @account0401
  Scenario: 04.01 User create new Personal Account
    Given User login with account "Emil"
    When User click on Show Navigation Menu icon
    When User select "Accounts" Navigation Item
    Then Able to see that "Accounts" tab is visible on page
    When User click on "New" button on Saleforce page
    Then Able to see New Account form is visible
    When User click on "Person Account" New Account type select check box
    And User click on "Next" button in New record type select Form
    Then Able to see "Person Account" New Account Form loaded
#
#    When Get new lead data from excel file
#    When User update customer information in New Lead Form
##    And User click on "Save" button in New Lead Form
#
#    When Wait for 5 seconds
#    When User click on Show Navigation Menu icon
#    When User select "Leads" Navigation Item
#    Then Verify new Leads record created with correct data on Lead listing page