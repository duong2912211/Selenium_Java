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
#    And User click on button with text "Absenden"

    And User open new tab
    Given User login with account "Emil"
    And User click on Show Navigation Menu icon
    And User select "Leads" Navigation Item
    Then Verify new Leads record created with correct data on Lead listing page

    When User click on record new create with excel data
    And Wait for 5 seconds

    Then Able to see "Lead" record have correct name with excel data
    And Able to see Lead Partner Info is "All information available to forward to SEAT partner"
    And Able to see in <Section> section, have <Field Name> text field with data is <Expected Value> identical with <Expected Data Source> data source
      | Section             | Field Name       | Expected Value   | Expected Data Source |
      | Contact Information | Name             | Name             | Excel                |
      | Contact Information | Mobile           | Mobile           | Excel                |
      | Contact Information | Phone            | Phone            | Excel                |
      | Contact Information | Email            | Email            | Excel                |
      | Contact Information | Address          | Street           | Excel                |
      | Contact Information | Address          | Zip and City     | Excel                |
      | Contact Information | Customer Type    | "Test Drive"     | Fixed                |
      | Vehicle Information | Vehicle Interest | Vehicle Interest | Excel                |
      | Lead Information    | Lead Status      | "in Progress"    | Fixed                |
