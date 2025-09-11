@homepage @Functional
Feature: Test Drive Web Form Test

  @webform0301
  Scenario: 03.01 User complete Test Drive Web Form successfully
    Given User navigate to Test Drive web form
    Then Able to see that web form title is "Lust auf eine Probefahrt" visible on page
    When Get new lead data from excel file
    And User select interested vehicle with excel data
    And User click on button with text "Weiter"

    Then Able to see that web form title is "Probefahrt anfragen." visible on page
    When User input "Vorname" field in webform with excel data
    And User input "Nachname" field in webform with excel data
    And User input "E-Mail" field in webform with excel data
    And User input "Telefonnummer" field in webform with excel data

    And User search SEAT Partner with "10319 Berlin"
    And User select SEAT Partner "10319 Berlin" from suggestion list
    And User select SEAT Partner "Volkswagen Automobile Berlin GmbH - CUPRA Garage Berlin" from carousel
    
    And User ticks consent checkbox for "E-Mail"
    And User ticks consent checkbox for "Telefon"

