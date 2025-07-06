package step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.HomePage;
import runner.Hooks;

public class homeSteps {
    HomePage homePage;

    @Given("User access to Homepage")
    public void userOnHomePage() {
        homePage = new HomePage(Hooks.getDriver());
        homePage.navigateToHomePage();
    }

    @Then("Able to see that {string} category is visible")
    public void ableToSeeThatCategoryIsVisible(String categoryName) {
        homePage.verifyCategoryVisible(categoryName);
    }
}
