package step_definition;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.LoginPage;
import runner.DriverManager;

public class loginSteps {

    private final LoginPage loginPage;

    public loginSteps() {
        this.loginPage = new LoginPage(DriverManager.getDriver());
    }

    @Given("User access to Login page")
    public void userAccessToLoginPage() {
        loginPage.navigateToPage("");
    }

    @Then("Able to see that {string} link is visible")
    public void ableToSeeThatLinkIsVisible(String linkPlaceholderText) {
        loginPage.verifyLinksIsVisible(linkPlaceholderText);
    }
}