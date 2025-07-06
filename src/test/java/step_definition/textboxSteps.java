package step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.TextBoxPage;
import runner.Hooks;

public class textboxSteps {

    TextBoxPage textBoxPage = new TextBoxPage(Hooks.getDriver());

    @Given("User on {string} page")
    public void userOnPage(String page) {
        textBoxPage.navigateToPage(page);
    }

    @Then("Able to see that user should log in successfully")
    public void ableToSeeThatUserLogInSuccess() {
        System.out.println("step4");
    }
}
