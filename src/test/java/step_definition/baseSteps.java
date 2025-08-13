package step_definition;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class baseSteps extends BasePage {
    BasePage basePage;

    public baseSteps(WebDriver driver) {
        super(driver);
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
        basePage.clickOnButtonWithName(buttonName);
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
}
