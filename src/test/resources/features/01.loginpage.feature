@loginpage @Functional
Feature: Login Page Test

  @login0101
  Scenario: 01.01 User access to Login page successfully
    Given User access to Login page
    Then Able to see that "Username" input field is visible
    Then Able to see that "Password" input field is visible
    Then Able to see that "To access this page, you have to log in to Salesforce." error message is visible
#    Then Able to see that "" checkbox is visible
#    Then Able to see that "" link is visible