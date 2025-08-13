package step_definition;

import io.cucumber.java.en.Given;
import pages.LoginPage;

public class loginSteps {

    private final LoginPage loginPage;

    // Constructor injection - PicoContainer will inject LoginPage which gets WebDriver from Hooks
    public loginSteps(LoginPage loginPage) {
        this.loginPage = loginPage;
    }

    @Given("User access to Login page")
    public void userAccessToLoginPage() {
        loginPage.navigateToPage("*");
    }
}