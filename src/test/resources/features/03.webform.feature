@homepage @Functional
Feature: Test Drive Web Form Test

  @webform0301
  Scenario: 03.01 User complete Test Drive Web Form successfully
    Given User navigate to Test Drive web form
    Then Able to see that web form title is "Lust auf eine Probefahrt" "mit deinem Wunschmodell?" visible on page
    When Get new lead data from excel file
    And User select interested vehicle with excel data
    And User click on button with text "Weiter"

    Then Able to see that web form title is "Probefahrt anfragen." visible on page
    When User select "" option for "" select field on web form
    And User input "" field in webform with excel data

