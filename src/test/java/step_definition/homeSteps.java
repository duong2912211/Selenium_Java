package step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import runner.DriverManager;

public class homeSteps {
    private final HomePage homePage;

    public homeSteps() {
        this.homePage = new HomePage(DriverManager.getDriver());
    }

    @Given("User access to Homepage")
    public void userOnHomePage() {
        homePage.navigateToPage("");
    }

    @Then("Able to see that {string} category is visible")
    public void ableToSeeThatCategoryIsVisible(String categoryName) {
    }

    @When("User click on {string} category")
    public void userClickOnCategoryNameItem(String categoryName) {
    }

    @Then("Able to see that all menu in {string} extended")
    public void ableToSeeThatMenuExtended(String categoryName) {
        homePage.verifyMenuListExtended(categoryName);
    }

    @When("User click on {string} side menu")
    public void userClickOnSideMenu(String menuName) {
        homePage.clickOnSideMenu(menuName);
    }
}
