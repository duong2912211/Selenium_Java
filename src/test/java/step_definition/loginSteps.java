package step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.LoginPage;
import runner.ConfigReader;
import runner.DriverManager;

public class loginSteps {

    private final LoginPage loginPage;

    public loginSteps() {
        this.loginPage = new LoginPage(DriverManager.getDriver());
    }

    @Given("User access to Login page")
    public void userAccessToLoginPage() {
        loginPage.navigateToPage("cem_saleforce_url");
    }

    @Given("User login with account {string}")
    public void userLoginWithAccount(String acc) {
        String env = System.getProperty("env", "dev"); // default to 'dev'
        ConfigReader.load(env);

        loginPage.navigateToPage("cem_saleforce_url");
        loginPage.enterValueToInputField("username", ConfigReader.get("base." + acc.toLowerCase().trim() + ".username"));
        loginPage.enterValueToInputField("password", ConfigReader.get("base." + acc.toLowerCase().trim() + ".password"));
        loginPage.clickOnButtonWithId("Login");
    }

    @Then("Able to see that {string} link is visible")
    public void ableToSeeThatLinkIsVisible(String linkPlaceholderText) {
        loginPage.verifyLinksIsVisible(linkPlaceholderText);
    }
}