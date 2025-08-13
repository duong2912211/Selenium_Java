package step_definition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;

public class homeSteps {
    private final HomePage homePage;

    public homeSteps(HomePage homePage) {
        this.homePage = homePage;
    }

    @Given("User access to Homepage")
    public void userOnHomePage() {
        homePage.navigateToPage("");
    }

    @Then("Able to see that {string} category is visible")
    public void ableToSeeThatCategoryIsVisible(String categoryName) {
        homePage.verifyCategoryVisible(categoryName);
    }

    @When("User click on {string} category")
    public void userClickOnCategoryNameItem(String categoryName) {
        homePage.clickOnCategoryMenu(categoryName);
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
