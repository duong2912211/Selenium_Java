@textbox
Feature: Text Box Page Test

  Background:
    Given User access to HomePage
  @test1
Scenario: 01.01. Login successfully
  Given User on "login" page
  When User enter "username" field value ""
  And User enter "password" field value ""
  And User click on "Login" button
  Then Able to see that user should log in successfully