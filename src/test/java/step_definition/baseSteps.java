package step_definition;

import helper.ExcelHandler;
import helper.Helper;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import runner.DriverManager;

import java.io.IOException;

public class baseSteps {
    private final BasePage basePage;

    public baseSteps() {
        // ✅ Use factory method to create BasePage instance
        this.basePage = BasePage.createGenericPage(DriverManager.getDriver());
    }

    @When("User navigate to {string} page")
    public void userNavigateToPage(String page) {

    }

    @When("User enter {string} input field value {string}")
    public void userEnterFieldWithValue(String fieldName, String value) {
        basePage.enterValueToInputField(fieldName, value);
    }

    @When("User click on {string} button")
    public void userClickOnButton(String buttonName) {
        basePage.clickOnButtonWithId(buttonName);
    }

    @When("User enter {string} text area value {string}")
    public void userEnterTextAreaValue(String fieldName, String value) {
        basePage.enterValueToTextArea(fieldName, value);
    }

    @Then("Able to see that {string} input field is visible")
    public void ableToSeeThatInputFieldIsVisible(String fieldName) {
        basePage.verifyInputFieldIsVisible(fieldName);
    }

    @Then("Able to see that {string} error message is visible")
    public void ableToSeeThatErrorMessageIsVisible(String errorMsg) {
        basePage.verifyErrorMessageIsVisible(errorMsg);
    }

    @Then("Able to see that {string} button is visible")
    public void ableToSeeThatButtonIsVisible(String buttonText) {
        basePage.verifyButtonIsVisible(buttonText);
    }

    @Then("Able to see that {string} apps name on page")
    public void ableToSeeThatAppsNameOnPage(String appName) {
        basePage.verifyAppNameIsVisible(appName);
    }

    @Then("Able to see that {string} tab is visible on page")
    public void ableToSeeThatStringTabIsVisibleOnPage(String tabName) {
        basePage.verifyTabIsVisible(tabName);
    }

    @And("User update {string} search field with {string}")
    public void userUpdateSearchFieldWith(String fieldName, String data) {
        basePage.selectOptionForSearchField(fieldName, data);
    }

    @When("Wait for {int} seconds")
    public void iWaitForSeconds(int seconds) throws InterruptedException {
        Helper.pause(seconds);
    }

    @When("User click on Show Navigation Menu icon")
    public void userClickOnShowNavigationMenuIcon() {
        basePage.clickOnShowNavigationMenuIcon();
    }

    @When("User select {string} Navigation Item")
    public void userSelectNavigationItem(String item) {
        basePage.selectNavigationMenu(item);
    }

    @When("Get new lead data from excel file")
    public void getUserDataFromExcelFile() throws IOException {
        ExcelHandler.readExcelFile();
    }

    @When("User click on record new create with excel data")
    public void userClickOnRecordNewCreateWithExcelData() {
        String recordName = basePage.getDataInJsonWithScenarioNumber("firstname") + " " + basePage.getDataInJsonWithScenarioNumber("lastname");
        basePage.clickOnRecordOnListingTable(recordName);
    }

    @Then("Able to see {string} record have correct name with excel data")
    public void ableToSeeRecordHaveCorrectNameWithExcelData(String recordType) {
        String recordName = basePage.getDataInJsonWithScenarioNumber("firstname") + " " + basePage.getDataInJsonWithScenarioNumber("lastname");
        basePage.verifyRecordTitle(recordType,recordName);
    }

    @And("Able to see secondary field {string} data similar with excel data")
    public void ableToSeeSecondaryFieldDataSimilarWithExcelData(String secondaryFieldTitle) {
        String[] titleList = secondaryFieldTitle.split(",");
        for (String title : titleList) {
            basePage.verifySecondaryFieldData(title);
        }
    }

    @And("User click on button with text {string}")
    public void userClickOnButtonWithText(String buttonText) {
        basePage.clickOnButtonWithText(buttonText);
    }

    @When("User select {string} option for {string} select field on web form")
    public void userSelectValueForSelectField(String option, String selectField) {
//        basePage.selectOptionForSelectorField();
    }

    @When("User click on {string} button on Saleforce page")
    public void userClickOnQuickActionButtonOnLeadsPage(String buttonName) {
        basePage.clickQuickActionButton(buttonName);
    }

    @When("User click on {string} New record type select check box")
    public void userClickOnNewLeadTypeCheckBox(String type) {
        basePage.clickOnNewRecordTypeSelectCheckBox(type);
    }

    @And("User click on {string} button in New record type select Form")
    public void userClickOnButtonInNewLeadTypeSelectForm(String button) {
        basePage.clickOnButtonInNewRecordSelectTypeForm(button);
    }

    @When("User switch to filter {string}")
    public void userSwitchToFilter(String filterName){
        basePage.switchFilterWithName(filterName);
    }

    @When("User click on record with name {string}")
    public void userClickOnRecordWithName(String recordName) {
        basePage.clickOnRecordOnListingTable(recordName);
    }

    @When("User open new tab")
    public void userOpenNewTab() {
        basePage.openNewTab();
    }
}
