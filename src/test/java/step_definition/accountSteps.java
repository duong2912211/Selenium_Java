package step_definition;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.accountPage;
import runner.DriverManager;

public class accountSteps {

    private final accountPage accountPage;

    public accountSteps() {
        this.accountPage = new accountPage(DriverManager.getDriver());
    }

    @Then("Able to see New Account form is visible")
    public void ableToSeeNewAccountFormIsVisible() {
        accountPage.verifyNewAccountTypeSelectFormVisible();
    }

    @When("User click on {string} New Account type select check box")
    public void userClickOnNewAccountTypeSelectCheckBox(String type) {
        accountPage.clickOnNewRecordTypeSelectCheckBox(type);
    }

    @Then("Able to see {string} New Account Form loaded")
    public void ableToSeeNewAccountFormLoaded(String type) {
        accountPage.verifyNewAccountFormLoaded(type);
    }
}
