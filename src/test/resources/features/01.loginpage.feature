@loginpage @Functional
Feature: Login Page Test

  @login0101
  Scenario: 01.01 User access to Login page successfully
    Given User access to Login page
    Then Able to see that "Username" input field is visible
    And Able to see that "Password" input field is visible
    And Able to see that "To access this page, you have to log in to Salesforce." error message is visible
    And Able to see that "Login" button is visible
#    And Able to see that "" checkbox is visible
    And Able to see that "Forgot Your Password?" link is visible

  @login0102
  Scenario: 01.02 User login successfully
    Given User access to Login page
    When User enter "Username" input field value "epicseven211942@agentforce.com"
    And User enter "Password" input field value "Duong2912@"
    And User click on "Login" button
    Then Able to see that "Sales" apps name on page
