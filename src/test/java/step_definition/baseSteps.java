package step_definition;

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
}
