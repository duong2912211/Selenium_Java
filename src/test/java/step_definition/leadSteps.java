package step_definition;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LeadPage;
import pages.LoginPage;
import runner.DriverManager;

import java.io.IOException;

public class leadSteps {

    private final LeadPage leadPage;

    public leadSteps() {
        this.leadPage = new LeadPage(DriverManager.getDriver());
    }

    @When("User click on {string} button on Leads page")
    public void userClickOnQuickActionButtonOnLeadsPage(String buttonName) {
        leadPage.clickQuickActionButtonOnLeadPage(buttonName);
    }

    @Then("Able to see New Lead form is visible")
    public void ableToSeeNewLeadFormIsVisible() {
        leadPage.verifyNewLeadTypeSelectFormVisible();
    }

    @When("User click on {string} New Lead type check box")
    public void userClickOnNewLeadTypeCheckBox(String type) {
        leadPage.clickOnNewLeadTypeCheckBox(type);
    }

    @When("User click on {string} button in New Lead Form")
    public void userClickOnButtonInNewLeadForm(String button){
        leadPage.clickOnButtonInNewLeadForm(button);
    }

    @Then("Able to see {string} New Lead Form loaded")
    public void ableToSeeNewLeadFormLoaded(String formType) {
        leadPage.verifyNewLeadCreationFormVisible(formType);
    }

    @When("User update customer information in New Lead Form")
    public void userUpdateCustomerInformationInNewLeadForm() throws IOException {
        leadPage.updateNewLeadFormWithExcelData();
    }
}
