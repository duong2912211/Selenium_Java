package step_definition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
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

    @Then("Able to see Lead Dealer Information have correct data with excel data")
    public void ableToSeeLeadDealerInformationHaveCorrectDataWithExcelData() {
        leadPage.verifyLeadDealerInformationWithExcelData();
    }

//    @Then("I should verify Lead Details Record with data:")
//    public void iShouldVerifyLeadDetailsRecordWithData(DataTable dataTable) {
//        List<Map<String, String>> sections = dataTable.asMaps();
//
//        for (Map<String, String> row : sections) {
//            String sectionName = row.get("Section");
//            String excelFields = row.get("Excel Fields");
//            String fixedFields = row.get("Fixed Fields");
//            String fixedValues = row.get("Fixed Values");
//
//            // Process Excel fields
//            if (excelFields != null && !excelFields.trim().isEmpty()) {
//                leadPage.processExcelFields(sectionName, excelFields);
//            }
//
//            // Process Fixed fields
//            if (fixedFields != null && !fixedFields.trim().isEmpty()) {
//                leadPage.processFixedFields(sectionName, fixedFields, fixedValues);
//            }
//        }
//    }

//    @Then("Able to see in {string} section, have {string} text field with data is {string} identical with {string} data source")
//    public void ableToSeeInSectionHaveLightningTextFieldWithDataIsIdenticalWithData(String sectionName, String fieldName,String data, String dataSource) {
//        leadPage.verifyLightningTextField(sectionName,fieldName,dataSource,data);
//    }

    @And("Able to see in <Section> section, have <Field Name> text field with data is <Expected Value> identical with <Expected Data Source> data source")
    public void ableToSeeInSectionSectionHaveFieldNameTextFieldWithDataIsExpectedValueIdenticalWithExpectedDataSourceDataSource(DataTable dataTable) {
        List<Map<String, String>> fields = dataTable.asMaps();

        for (Map<String, String> field : fields) {
            String section = field.get("Section");
            String fieldName = field.get("Field Name");
            String expectedValue = field.get("Expected Value");
            String dataSource = field.get("Expected Data Source");

            leadPage.verifyLightningTextField(section,fieldName,dataSource,expectedValue);
            System.out.println("✓ Verified: " + section + " -> " + fieldName + " = " + expectedValue + " (" + dataSource + ")");
        }

    }
}
