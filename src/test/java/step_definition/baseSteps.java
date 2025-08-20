package step_definition;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import runner.DriverManager;

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
        basePage.enterValueToInputField(fieldName,value);
    }
    @When("User click on {string} button")
    public void userClickOnButton(String buttonName) {
        basePage.clickOnButtonWithId(buttonName);
    }

    @When("User enter {string} text area value {string}")
    public void userEnterTextAreaValue(String fieldName, String value) {
        basePage.enterValueToTextArea(fieldName,value);
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
        basePage.selectOptionForSearchField(fieldName,data);
    }
}
