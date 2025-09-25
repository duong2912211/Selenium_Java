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
#    Then Able to see Thank You screen after submit form

    And User open new tab
    And User login with account "Emil"
    And User click on Show Navigation Menu icon
    And User select "Leads" Navigation Item
    And User select "Test Latest Leads" filter
    And User search for "Lead" record field
    And Wait until new record created on SF
    Then Verify new Leads record created with correct data on Lead listing page

    When User click on record new create with excel data
    And Wait for 5 seconds

    Then Able to see "Lead" record have correct name with excel data
    When User click on "Details" tab in record overview
    Then Able to see that "Details" tab is selected in record overview
    Then Able to see "Name" field in "Contact Information" section have data similar with excel file
    Then Able to see "Phone" field in "Contact Information" section have data similar with excel file
    Then Able to see "Email" field in "Contact Information" section have data similar with excel file
    Then Able to see "Address" field in "Contact Information" section have data is "Germany"
    Then Able to see "Lead Record Type" field in "Contact Information" section have data is "Testdrive Lead"
    Then Able to see "Customer Type" field in "Contact Information" section have data is "Test Drive"

    Then Able to see "Vehicle Interest" field in "Vehicle Information" section have data similar with excel file

    Then Able to see "Source" field in "Lead Information" section have data is "Website"
    Then Able to see "Request" field in "Lead Information" section have data is "Test Drive"
    Then Able to see "Lead Status" field in "Lead Information" section have data is "Forwarded to dealer"

    Then Able to see "Dealer" field in "Dealer Information" section have data is "SEAT Bank Zweigniederlassung der Volkswagen Bank GmbH"

    Then Able to see "Source System" field in "System Information" section have data is "CEM"

    When User click on "Data Privacy" tab in record overview
    Then Able to see that "Data Privacy" tab is selected in record overview