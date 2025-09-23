package pages;

import helper.PhoneDE;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static helper.PhoneDE.equalsIgnoringFormatting;
import static locators.elements.*;

public class leadPage extends BasePage {

    public leadPage(WebDriver driver) {
        super(driver);
    }

    public static List<String> buildXPathVariations(String fieldName, String expectedValue) {
        List<String> xpaths = new ArrayList<>();

        xpaths.add(String.format(CONTACT_RECORD_TYPE_FIELD, fieldName, expectedValue));
        xpaths.add(String.format(CONTACT_NAME_FIELD, fieldName, expectedValue));
        xpaths.add(String.format(CONTACT_ADDRESS_FIELD, fieldName, expectedValue));
        xpaths.add(String.format(LIGHTNING_TEXT_FIELD, fieldName, expectedValue));
        xpaths.add(String.format(HREF_FIELD, fieldName, expectedValue));
        xpaths.add(String.format(FORCE_LOOKUP_FIELD, fieldName, expectedValue));

        return xpaths;
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
        searchRecordWithName("Lead", expectedName);

        //Verify number and records match with new lead full name
        By leadListingTable = By.xpath(RECORD_LISTING_TABLE);
        verifyElementVisible(leadListingTable);

        //Get the maximum records on the listing page
        String rowNumber = driver.findElement(leadListingTable).getAttribute("data-num-rows");
        for (int i = 1; i <= Integer.parseInt(Objects.requireNonNull(rowNumber)); i++) {
            String rowXpath = "//tr[@data-row-number='" + i + "']";
            Assert.assertEquals(expectedName, driver.findElement(By.xpath(rowXpath + "//th[@data-label='Name']//slot/span")).getText());
            Assert.assertTrue(equalsIgnoringFormatting(getDataInJsonWithScenarioNumber("mobile"), driver.findElement(By.xpath(rowXpath + "//td[@data-label='Mobile']//a")).getText()));
            Assert.assertTrue(equalsIgnoringFormatting(getDataInJsonWithScenarioNumber("phone"), driver.findElement(By.xpath(rowXpath + "//td[@data-label='Phone']//a")).getText()));
            Assert.assertEquals("SEAT", driver.findElement(By.xpath(rowXpath + "//td[@data-label='Brand']//lst-formatted-text/span")).getText());
        }
    }

    public void verifyPartnerInformation(String expectedValue) {
        String xpath = String.format(PARTNER_INFO_VALUE, expectedValue);

        WebElement element = driver.findElement(By.xpath(xpath));
        Assert.assertTrue(element.isDisplayed());
    }

    public void verifyLeadContactInformationWithExcelData() {
        verifyElementVisible(By.xpath(String.format(CONTACT_NAME_FIELD, getDataInJsonWithScenarioNumber("firstname") + " " + getDataInJsonWithScenarioNumber("lastname"))));
        verifyElementVisible(By.xpath(String.format(CONTACT_RECORD_TYPE_FIELD, "Unqualified Lead")));
        verifyElementVisible(By.xpath(String.format(HREF_FIELD, "Mobile", "tel:" + PhoneDE.toE164(getDataInJsonWithScenarioNumber("mobile")))));
        verifyElementVisible(By.xpath(String.format(HREF_FIELD, "Phone", "tel:" + PhoneDE.toE164(getDataInJsonWithScenarioNumber("phone")))));
        verifyElementVisible(By.xpath(String.format(LIGHTNING_TEXT_FIELD, "Customer Type", "Test Drive")));
        verifyElementVisible(By.xpath(String.format(HREF_FIELD, "Email", "mailto:" + getDataInJsonWithScenarioNumber("email --> nomail.com"))));
        verifyElementVisible(By.xpath(String.format(CONTACT_ADDRESS_FIELD, getDataInJsonWithScenarioNumber("street"))));
        verifyElementVisible(By.xpath(String.format(CONTACT_ADDRESS_FIELD, getDataInJsonWithScenarioNumber("zip") + " " + getDataInJsonWithScenarioNumber("city"))));
        verifyElementVisible(By.xpath(String.format(CONTACT_ADDRESS_FIELD, "Germany")));
    }

    public void verifyLeadVehicleInformationWithExcelData() {
        verifyElementVisible(By.xpath(String.format(FORCE_LOOKUP_FIELD, "Vehicle Interest", getDataInJsonWithScenarioNumber("vehicle interest"))));
        verifyElementVisible(By.xpath(String.format(LIGHTNING_TEXT_FIELD, "New / Used", "New")));
    }

    public void verifyLeadInformationWithExcelData() {
        verifyElementVisible(By.xpath(String.format(FORCE_LOOKUP_FIELD, "Lead Owner", "Emil (AIA Team Manager)")));
        verifyElementVisible(By.xpath(String.format(LIGHTNING_TEXT_FIELD, "Lead Status", "in Progress")));
    }

    public void verifyLeadDealerInformationWithExcelData() {
        verifyElementVisible(By.xpath(String.format(FORCE_LOOKUP_FIELD, "Dealer", getDataInJsonWithScenarioNumber("test dealer"))));
    }

    public void verifyLightningTextField(String header, String fieldName, String dataSource, String fixedExpectedData) {
        //Verify header
        try {
            verifyElementVisible(By.xpath(String.format(SECTION_EXPANDED_STATUS, header, true)));
        } catch (Exception e) {
            click(By.xpath(String.format(SECTION_EXPAND_BUTTON, header)));
        }
        // Get expected value based on data source
        String expectedValue = "";
        if (dataSource.trim().equalsIgnoreCase("excel")) {
            // Handle special email case
            switch (fixedExpectedData) {
                case "Email":
                    expectedValue = "mailto:" + getDataInJsonWithScenarioNumber("email --> nomail.com");
                    break;
                case "Mobile":
                    expectedValue = "tel:" + PhoneDE.toE164(getDataInJsonWithScenarioNumber("mobile"));
                    break;
                case "Phone":
                    expectedValue = "tel:" + PhoneDE.toE164(getDataInJsonWithScenarioNumber("phone"));
                    break;
                case "Zip and City":
                    expectedValue = getDataInJsonWithScenarioNumber("zip") + " " + getDataInJsonWithScenarioNumber("city");
                    break;
                case "Name":
                    expectedValue = getDataInJsonWithScenarioNumber("firstname") + " " + getDataInJsonWithScenarioNumber("lastname");
                    break;
                default:
                    expectedValue = getDataInJsonWithScenarioNumber(fixedExpectedData.toLowerCase());
            }
        } else if (dataSource.trim().equalsIgnoreCase("fixed")) {
            expectedValue = fixedExpectedData.replace("\"", "");
        }
        System.out.println(expectedValue);

        // Build multiple XPath variations for this field
        List<String> xpaths = buildXPathVariations(fieldName, expectedValue);

        // Try each XPath until one works
        boolean found = xpaths.stream().anyMatch(xpath -> {
            try {
                verifyElementVisible(By.xpath(xpath));
                scrollToElement(By.xpath(xpath));
                return true;
            } catch (Exception e) {
                return false;
            }
        });

        Assert.assertTrue(found);
    }
}
