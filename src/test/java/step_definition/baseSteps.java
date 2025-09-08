package step_definition;

import helper.ExcelHandler;
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
        Thread.sleep(seconds * 1000L);
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

    @And("User click on button with text {string}")
    public void userClickOnButtonWithText(String buttonText) {
        basePage.clickOnButtonWithText(buttonText);
    }

    @When("User select {string} option for {string} select field on web form")
    public void userSelectValueForSelectField(String option, String selectField) {
        basePage.selectOptionForSelectorField();
    }
}
