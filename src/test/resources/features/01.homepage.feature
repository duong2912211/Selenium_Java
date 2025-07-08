@homepage
Feature: Home Page Test

  @test0101
  Scenario: 01.01 User access to Home page successfully
    Given User access to Homepage
    Then Able to see that "Elements" category is visible
    And Able to see that "Forms" category is visible
    And Able to see that "Alerts, Frame & Windows" category is visible
    And Able to see that "Widgets" category is visible
    And Able to see that "Interactions" category is visible
    And Able to see that "Book Store Application" category is visible

  @test0102
  Scenario Outline: 01.02 User can navigate to other page by click category menu
    Given User access to Homepage
    When User click on "<categoryName>" menu
    Then Able to see that all menu in "<categoryName>" extended

    Examples:
    |categoryName           |
    |Elements               |
    |Forms                  |
    |Alerts, Frame & Windows|
    |Widgets                |
    |Interactions           |
    |Book Store Application |