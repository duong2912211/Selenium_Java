@textbox @Functional
Feature: Text Box Page Test

  Background:
    Given User access to Homepage
    When User click on "Elements" category
    And User click on "Text Box" side menu

  @test0201
  Scenario: 02.01. User input data and submit successfully
    When User enter "userName" input field value "Nguyen Duong"
    And User enter "userEmail" input field value "duongtest@gmail.com"
    And User enter "currentAddress" text area value "Test Current Address"
    And User enter "permanentAddress " text area value "Test Permanent Address"
    And User click on "Submit" button
