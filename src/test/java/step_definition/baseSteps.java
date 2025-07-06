package step_definition;

import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class baseSteps extends BasePage {
    public baseSteps(WebDriver driver) {
        super(driver);
    }

    @When("User navigate to {string} page")
    public void userNavigateToPage(String page) {

    }
    @When("User enter {string} field value {string}")
    public void userEnterFieldWithValue(String fieldName, String value) {

    }
    @When("User click on {string} button")
    public void userClickOnButton(String buttonName) {
        System.out.println("step3");
    }

}
