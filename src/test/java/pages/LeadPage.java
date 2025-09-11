package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Objects;

import static helper.PhoneDE.equalsIgnoringFormatting;
import static locators.elements.*;

public class LeadPage extends BasePage {

    public LeadPage(WebDriver driver) {
        super(driver);
    }

    public void clickQuickActionButtonOnLeadPage(String buttonName) {
        By button = By.xpath(String.format(QUICK_ACTION_BUTTON, buttonName));
        verifyElementClickable(button);
        click(button);
    }

    public void verifyNewLeadTypeSelectFormVisible() {
        verifyElementVisible(By.xpath(String.format(NEW_LEAD_TYPE_SELECT_FORM_CHECKBOX, "Testdrive Lead")));
        verifyElementVisible(By.xpath(String.format(NEW_LEAD_TYPE_SELECT_FORM_CHECKBOX, "Fleet Lead")));
        verifyElementVisible(By.xpath(String.format(NEW_LEAD_TYPE_SELECT_FORM_CHECKBOX, "Unqualified Lead")));
        verifyElementVisible(By.xpath(String.format(NEW_LEAD_TYPE_SELECT_FORM_CHECKBOX, "User Chooser")));
    }

    public void clickOnNewLeadTypeSelectCheckBox(String type) {
        click(By.xpath(String.format(NEW_LEAD_TYPE_SELECT_FORM_CHECKBOX, type)));
    }

    public void clickOnButtonInNewLeadSelectTypeForm(String button) {
        click(By.xpath(String.format(NEW_LEAD_TYPE_SELECT_FORM_BUTTON, button)));
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

        verifyElementVisible(By.xpath(NEW_LEAD_CREATION_FORM));
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
        selectOptionForSearchField("Vehicle Interest", getDataInJsonWithScenarioNumber("interested vehicle"));
        selectOptionForSearchField("Dealer", getDataInJsonWithScenarioNumber("test dealer"));

    }

    public void verifyNewRecordShowOnLeadListingPage() throws InterruptedException {
        //Read the current data used to create Leads
        //Get the expected Full Name of Leads
        String expectedName = getDataInJsonWithScenarioNumber("firstname") + " " + getDataInJsonWithScenarioNumber("lastname");

        //Switch filter back to Test Latest Leads
        click(By.xpath(FILTER_LIST));
        click(By.xpath(String.format(FILTER_OPTION_WITH_TEXT, "Test Latest Leads")));
        Thread.sleep(3000);

        //Search for new lead record name
        enterValueToInputField("Lead-search-input", expectedName);
        pressEnter("Lead-search-input");
        Thread.sleep(3000);

        //Verify number and records match with new lead full name
        By leadListingTable = By.xpath(LEAD_LISTING_TABLE);

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
}
