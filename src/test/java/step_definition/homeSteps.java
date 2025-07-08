package step_definition;

import io.cucumber.java.an.E;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import locators.elements;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.HomePage;
import runner.Hooks;

import java.util.ArrayList;
import java.util.List;

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

    @When("User click on {string} menu")
    public void userClickOnCategoryNameMenu(String categoryName) {
        homePage.clickOnCategoryMenu(categoryName);
    }

  @Then("Able to see that all menu in {string} extended")
  public void ableToSeeThatMenuExtended(String categoryName) {
        homePage.verifyMenuListExtended(categoryName);
    }
}
