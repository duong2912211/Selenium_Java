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
}
