package step_definition;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.leadPage;
import runner.DriverManager;

import java.io.IOException;

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

    @And("Able to see Lead Partner Info is {string}")
    public void ableToSeePartnerInfoIs(String partnerInfo) {
        leadPage.verifyPartnerInformation(partnerInfo);
    }

    @And("Able to see Lead Contact Information have correct data with excel data")
    public void ableToSeeContactInformationHaveCorrectDataWithExcelData() {
        leadPage.verifyLeadContactInformationWithExcelData();
    }

    @And("Able to see Lead Vehicle Information have correct data with excel data")
    public void ableToSeeLeadVehicleInformationHaveCorrectDataWithExcelData() {
        leadPage.verifyLeadVehicleInformationWithExcelData();
    }

    @And("Able to see Lead Lead Information have correct data with excel data")
    public void ableToSeeLeadLeadInformationHaveCorrectDataWithExcelData() {
        leadPage.verifyLeadInformationWithExcelData();
    }

    @And("Able to see Lead Dealer Information have correct data with excel data")
    public void ableToSeeLeadDealerInformationHaveCorrectDataWithExcelData() {
        leadPage.verifyLeadDealerInformationWithExcelData();
    }
}
