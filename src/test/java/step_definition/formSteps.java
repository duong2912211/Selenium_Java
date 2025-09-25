package step_definition;

import io.cucumber.java.PendingException;
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

    // --- Navigation ---
    @Given("User navigate to Test Drive web form")
    public void userNavigateToTestDriveWebForm() {
        formPage.navigateToPage("seat_testdrive_webform");
    }

    // --- Title ---
    @Then("Able to see that web form title is {string} visible on page")
    public void ableToSeeThatWebFormTitleIsVisibleOnPage(String title) {
        formPage.verifyTestDriveTitle(title);
    }

    // --- Vehicle selection ---
    @When("User click on interested vehicle {string} checkbox")
    public void userClickOnInterestedVehicleCheckbox(String interestVehicle) {
        formPage.selectInterestVehicle(interestVehicle);
    }

    @When("User select interested vehicle with excel data")
    public void userSelectInterestedVehicleWithExcelData() {
        String vehicle = formPage.getDataInJsonWithScenarioNumber("vehicle interest");
        formPage.selectInterestVehicle(vehicle);
    }

    // --- Input fields ---
    @When("User input {string} field in webform with excel data")
    public void userInputFieldInWebformWithExcelData(String field) throws InterruptedException {
        formPage.enterValueToInputFieldInWebForm(field);
    }

    // --- SEAT Partner ---
    @When("User search SEAT Partner with {string}")
    public void userSearchSeatPartnerWith(String query) {
        formPage.searchSeatPartner(query);
    }

    @When("User select SEAT Partner {string} from suggestion list")
    public void userSelectSeatPartnerFromSuggestionList(String option) {
        formPage.selectSeatPartnerFromList(option);
    }

    @When("User select SEAT Partner {string} from carousel")
    public void userSelectSeatPartnerFromCarousel(String dealerName) {
        formPage.selectSeatPartnerFromCarousel(dealerName);
    }

    // --- Consent checkboxes ---
    @When("User ticks consent checkbox for {string}")
    public void userTicksConsentCheckboxFor(String consentType) {
        formPage.tickConsentCheckbox(consentType);
    }

    // --- Verification ---
    @Then("Able to see current car as {string}")
    public void userShouldSeeCurrentCarAs(String expectedCar) {
        formPage.verifyCurrentCar(expectedCar);
    }

    @Then("Able to see Thank You screen after submit form")
    public void ableToSeeThankYouScreenAfterSubmitForm() {
        formPage.verifyThankYouScreen();
    }
}