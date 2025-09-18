@leadpage @Functional
Feature: Leads Page Test

  @lead0201
  Scenario: 02.01 User create new Unqualified Leads
    Given User login with account "Emil"
    Then Able to see that "Leads" tab is visible on page
#    When User click on "New" button on Saleforce page
#    Then Able to see New Lead form is visible
#    When User click on "Unqualified Lead" New record type select check box
#    And User click on "Next" button in New record type select Form
#    Then Able to see "Unqualified Lead" New Lead Form loaded
#
    When Get new lead data from excel file
#    And User update customer information in New Lead Form
#    And User click on "Save" button in New Lead Form

    And Wait for 5 seconds
    And User click on Show Navigation Menu icon
    And User select "Leads" Navigation Item
    Then Verify new Leads record created with correct data on Lead listing page

    When User click on record new create with excel data
    And Wait for 5 seconds

    Then Able to see "Lead" record have correct name with excel data
    And Able to see secondary field "Vehicle Interest,Dealer IM,Phone,Mobile,Email" data similar with excel data
    And Able to see Lead Partner Info is "All information available to forward to SEAT partner"
    And Able to see Lead Contact Information have correct data with excel data
    And Able to see Lead Vehicle Information have correct data with excel data
    And Able to see Lead Lead Information have correct data with excel data
    And Able to see Lead Dealer Information have correct data with excel data


  @lead0202
  Scenario: 02.02 User create new Testdrive Leads
    Given User login with account "Emil"
    Then Able to see that "Leads" tab is visible on page
    When User click on "New" button on Saleforce page
    Then Able to see New Lead form is visible
    When User click on "Testdrive Lead" New record type select check box
    And User click on "Next" button in New record type select Form
    Then Able to see "Testdrive Lead" New Lead Form loaded

    When User update customer information in New Lead Form
    And User update "Vehicle Interest" search field with "SEAT Tarraco"
    And User update "Dealer" search field with "Test ISA"
#    And User click on "Save" button in New Lead Form

    When Wait for 5 seconds
    When User click on Show Navigation Menu icon
    When User select "Leads" Navigation Item
    Then Verify new Leads record created with correct data on Lead listing page