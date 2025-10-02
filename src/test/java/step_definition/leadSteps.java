package step_definition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.leadPage;
import runner.DriverManager;

import java.util.List;
import java.util.Map;

public class leadSteps {

    private final leadPage leadPage;

    public leadSteps() {
        this.leadPage = new leadPage(DriverManager.getDriver());
    }

    @Then("Able to see New Lead form is visible")
    public void ableToSeeNewLeadFormIsVisible() {
        leadPage.verifyNewLeadTypeSelectFormVisible();
    }

    @When("User click on {string} button in New Lead Form")
    public void userClickOnButtonInNewLeadForm(String button) {
        leadPage.clickOnButtonInNewLeadForm(button);
    }

    @Then("Able to see {string} New Lead Form loaded")
    public void ableToSeeNewLeadFormLoaded(String formType) {
        leadPage.verifyNewLeadCreationFormVisible(formType);
    }

    @When("User update customer information in New Lead Form")
    public void userUpdateCustomerInformationInNewLeadForm() {
        leadPage.updateNewLeadFormWithData();
    }

    @Then("Verify new Leads record created with correct data on Lead listing page")
    public void verifyNewLeadsRecordCreatedWithCorrectDataOnLeadListingPage() {
        leadPage.verifyNewRecordShowOnLeadListingPage();
    }

    @Then("Able to see {string} field in {string} section have data similar with excel file")
    public void ableToSeeFieldInSectionHaveDataSimilarWithExcelFile(String fieldName, String section) {
        leadPage.verifyLeadDetailsInformation(fieldName,section);
    }

    @Then("Able to see {string} field in {string} section have data is {string}")
    public void ableToSeeFieldInSectionHaveDataIs(String fieldName, String section, String expectedData) {
        leadPage.verifyLeadDetailsInformation(fieldName,section,expectedData);
    }

    @Then("Able to see consent information in Consent summary board")
    public void ableToSeeConsentInformationHaveStatus(DataTable consentData) {
        List<Map<String, String>> consents = consentData.asMaps();

        for (Map<String, String> consent : consents) {
            System.out.println(consent);
        }
    }

    @And("Wait until lead forwarded to dealer")
    public void waitUntilLeadForwardedToDealer() {
        leadPage.waitForLeadForwardedToDealer();
    }
}
