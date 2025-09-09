package step_definition;

import helper.TestContext;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.formPage;
import runner.DriverManager;

public class formSteps {
    private final formPage formPage;

    public formSteps() {
        this.formPage = new formPage(DriverManager.getDriver());
    }

    @Given("User navigate to Test Drive web form")
    public void userNavigateToTestDriveWebForm() {
        formPage.navigateToPage("seat_testdrive_webform");
    }

    @Then("Able to see that web form title is {string} visible on page")
    public void ableToSeeThatWebFormTitleIsVisibleOnPage(String title) {
        formPage.verifyTestDriveTitle(title);
    }

    @When("User click on interested vehicle {string} checkbox")
    public void userClickOnInterestedVehicleCheckbox(String interestVehicle) {
        formPage.selectInterestVehicle(interestVehicle);
    }

    @And("User select interested vehicle with excel data")
    public void userSelectInterestedVehicleWithExcelData() {
        String vehicle = formPage.getDataInJsonWithScenarioNumber("interested vehicle");
        formPage.selectInterestVehicle(vehicle);
    }

    @And("User input {string} field in webform with excel data")
    public void userInputFieldInWebformWithExcelData(String field) {
        formPage.enterValueToInputFieldInWebForm(field);
    }
}