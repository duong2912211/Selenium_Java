package step_definition;

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
    public void verifyNewLeadsRecordCreatedWithCorrectDataOnLeadListingPage() throws InterruptedException {
        leadPage.verifyNewRecordShowOnLeadListingPage();
    }
}
