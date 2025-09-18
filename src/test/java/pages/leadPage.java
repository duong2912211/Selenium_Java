package pages;

import helper.Helper;
import helper.PhoneDE;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;
import java.util.Objects;

import static helper.PhoneDE.equalsIgnoringFormatting;
import static locators.elements.*;

public class leadPage extends BasePage {

    public leadPage(WebDriver driver) {
        super(driver);
    }

    public void verifyNewLeadTypeSelectFormVisible() {
        verifyElementVisible(By.xpath(String.format(NEW_RECORD_TYPE_SELECT_FORM_CHECKBOX, "Testdrive Lead")));
        verifyElementVisible(By.xpath(String.format(NEW_RECORD_TYPE_SELECT_FORM_CHECKBOX, "Fleet Lead")));
        verifyElementVisible(By.xpath(String.format(NEW_RECORD_TYPE_SELECT_FORM_CHECKBOX, "Unqualified Lead")));
        verifyElementVisible(By.xpath(String.format(NEW_RECORD_TYPE_SELECT_FORM_CHECKBOX, "User Chooser")));
    }

    public void clickOnButtonInNewLeadForm(String buttonText) {
        String button = switch (buttonText) {
            case "Save" -> "SaveEdit";
            case "Save & New" -> "SaveAndNew";
            case "Cancel" -> "CancelEdit";
            default -> "";
        };
        click(By.xpath(String.format(NEW_LEAD_FORM_BUTTON, button)));
    }

    public void verifyNewLeadCreationFormVisible(String formType) {
        By formTitle = By.xpath(NEW_LEAD_CREATION_FORM_TITLE);
        verifyElementVisible(formTitle);
        Assert.assertEquals("New Lead: " + formType, getText(formTitle));

        verifyElementVisible(By.xpath(NEW_RECORD_CREATION_FORM));
    }

    public void updateNewLeadFormWithData() {
        enterValueToInputField("firstName", getDataInJsonWithScenarioNumber("firstname"));
        enterValueToInputField("lastName", getDataInJsonWithScenarioNumber("lastname"));
        enterValueToInputField("MobilePhone", getDataInJsonWithScenarioNumber("mobile"));
        enterValueToInputField("Phone", getDataInJsonWithScenarioNumber("phone"));
        enterValueToInputField("Email", getDataInJsonWithScenarioNumber("email --> nomail.com"));
        enterValueToTextArea("street", getDataInJsonWithScenarioNumber("street"));
        enterValueToInputField("postalCode", getDataInJsonWithScenarioNumber("zip"));
        enterValueToInputField("city", getDataInJsonWithScenarioNumber("city"));
        selectOptionForSelectorField("Customer Type", "Test Drive");
        selectOptionForSearchField("Vehicle Interest", getDataInJsonWithScenarioNumber("vehicle interest"));
        selectOptionForSearchField("Dealer", getDataInJsonWithScenarioNumber("test dealer"));

    }

    public void verifyNewRecordShowOnLeadListingPage() {
        //Read the current data used to create Leads
        //Get the expected Full Name of Leads
        String expectedName = getDataInJsonWithScenarioNumber("firstname") + " " + getDataInJsonWithScenarioNumber("lastname");

        //Switch filter back to Test Latest Leads
        switchFilterWithName("Test Latest Leads");

        //Search for new lead record name
        searchRecordWithName("Lead",expectedName);

        //Verify number and records match with new lead full name
        By leadListingTable = By.xpath(RECORD_LISTING_TABLE);

        if (verifyElementVisible(leadListingTable)) {
            //Get the maximum records on the listing page
            String rowNumber = driver.findElement(leadListingTable).getAttribute("data-num-rows");
            for (int i = 1; i <= Integer.parseInt(Objects.requireNonNull(rowNumber)); i++) {
                String rowXpath = "//tr[@data-row-number='" + i + "']";
                Assert.assertEquals(expectedName, driver.findElement(By.xpath(rowXpath + "//th[@data-label='Name']//slot/span")).getText());
                Assert.assertTrue(equalsIgnoringFormatting(getDataInJsonWithScenarioNumber("mobile"), driver.findElement(By.xpath(rowXpath + "//td[@data-label='Mobile']//a")).getText()));
                Assert.assertTrue(equalsIgnoringFormatting(getDataInJsonWithScenarioNumber("phone"), driver.findElement(By.xpath(rowXpath + "//td[@data-label='Phone']//a")).getText()));
                Assert.assertEquals("SEAT", driver.findElement(By.xpath(rowXpath + "//td[@data-label='Brand']//lst-formatted-text/span")).getText());
            }
        } else throw new RuntimeException("No record found");
    }

    public void verifyPartnerInformation(String expectedValue){
        String xpath = String.format(PARTNER_INFO_VALUE, expectedValue);

        WebElement element = driver.findElement(By.xpath(xpath));
        Assert.assertTrue(element.isDisplayed());
    }

    public void verifyLeadContactInformationWithExcelData(){
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(CONTACT_NAME_FIELD,getDataInJsonWithScenarioNumber("firstname") + " " + getDataInJsonWithScenarioNumber("lastname")))));
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(CONTACT_RECORD_TYPE_FIELD,"Unqualified Lead"))));
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(CONTACT_MOBILE_FIELD, PhoneDE.toE164(getDataInJsonWithScenarioNumber("mobile"))))));
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(CONTACT_PHONE_FIELD, PhoneDE.toE164(getDataInJsonWithScenarioNumber("phone"))))));
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(CONTACT_CUSTOMER_TYPE_FIELD,"Test Drive"))));
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(CONTACT_EMAIL_FIELD,getDataInJsonWithScenarioNumber("email --> nomail.com")))));
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(CONTACT_ADDRESS_FIELD,getDataInJsonWithScenarioNumber("street")))));
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(CONTACT_ADDRESS_FIELD,getDataInJsonWithScenarioNumber("zip") + " " + getDataInJsonWithScenarioNumber("city")))));
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(CONTACT_ADDRESS_FIELD,"Germany"))));
    }

    public void verifyLeadVehicleInformationWithExcelData(){
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(VEHICLE_INTEREST_FIELD,getDataInJsonWithScenarioNumber("vehicle interest")))));
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(NEW_USED_FIELD,"New"))));
    }

    public void verifyLeadInformationWithExcelData(){
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(LEAD_OWNER_FIELD,"Emil"))));
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(LEAD_STATUS_FIELD,"in Progress"))));
    }

    public void verifyLeadDealerInformationWithExcelData(){
        Assert.assertTrue(verifyElementVisible(By.xpath(String.format(DEALER_FIELD,getDataInJsonWithScenarioNumber("test dealer")))));
    }
}
