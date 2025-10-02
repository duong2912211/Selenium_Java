package pages;

import helper.PhoneDE;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
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

        //Verify number and records match with new lead full name
        By leadListingTable = By.xpath(RECORD_LISTING_TABLE);
        verifyElementVisible(leadListingTable);

        //Get the maximum records on the listing page
        String rowNumber = driver.findElement(leadListingTable).getAttribute("data-num-rows");
        for (int i = 1; i <= Integer.parseInt(Objects.requireNonNull(rowNumber)); i++) {
            String rowXpath = "//tr[@data-row-number='" + i + "']";
            Assert.assertEquals(expectedName, driver.findElement(By.xpath(rowXpath + "//th[@data-label='Name']//slot/span")).getText());
            Assert.assertTrue(equalsIgnoringFormatting(getDataInJsonWithScenarioNumber("phone"), driver.findElement(By.xpath(rowXpath + "//td[@data-label='Phone']//a")).getText()));
            Assert.assertEquals("SEAT", driver.findElement(By.xpath(rowXpath + "//td[@data-label='Brand']//lst-formatted-text/span")).getText());
        }
    }

    public void verifyLeadDetailsInformation(String fieldName, String section) {
        scrollToElement(By.xpath(String.format(SECTION_HEADER, section)));
        if (section.equals("Contact Information")) {
            switch (fieldName) {
                case "Name":
                    verifyElementVisible(By.xpath(String.format(CONTACT_NAME_FIELD, getDataInJsonWithScenarioNumber("firstname") + " " + getDataInJsonWithScenarioNumber("lastname"))));
                    break;
                case "Phone":
                    verifyElementVisible(By.xpath(String.format(PHONE_HREF_FIELD, fieldName, PhoneDE.toE164(getDataInJsonWithScenarioNumber("phone")))));
                    break;
                case "Mobile":
                    verifyElementVisible(By.xpath(String.format(PHONE_HREF_FIELD, fieldName, PhoneDE.toE164(getDataInJsonWithScenarioNumber("mobile")))));
                    break;
                case "Email":
                    verifyElementVisible(By.xpath(String.format(MAIL_HREF_FIELD, fieldName, getDataInJsonWithScenarioNumber("email --> nomail.com"))));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field name");
            }
        } else if (section.equals("Vehicle Information")) {
            if (fieldName.equals("Vehicle Interest")) {
                verifyElementVisible(By.xpath(String.format(FORCE_LOOKUP_FIELD, fieldName, getDataInJsonWithScenarioNumber("vehicle interest"))));
            } else {
                throw new IllegalArgumentException("Invalid field name");
            }
        }
    }

    public void verifyLeadDetailsInformation(String fieldName, String section, String expectedData) {
        scrollToElement(By.xpath(String.format(SECTION_HEADER, section)));
        if (section.equals("Contact Information")) {
            switch (fieldName) {
                case "Lead Record Type":
                    verifyElementVisible(By.xpath(String.format(CONTACT_RECORD_TYPE_FIELD, expectedData)));
                    break;
                case "Customer Type":
                    verifyElementVisible(By.xpath(String.format(LIGHTNING_TEXT_FIELD, "Customer Type", expectedData)));
                    break;
                case "Address":
                    verifyElementVisible(By.xpath(String.format(CONTACT_ADDRESS_FIELD, "Address", expectedData)));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field name");
            }
        } else if (section.equals("Lead Information")) {
            switch (fieldName) {
                case "Source", "Request", "Lead Status":
                    verifyElementVisible(By.xpath(String.format(LIGHTNING_TEXT_FIELD, fieldName, expectedData)));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field name");
            }
        } else if (section.equals("Dealer Information")) {
            switch (fieldName) {
                case "Dealer":
                    verifyElementVisible(By.xpath(String.format(FORCE_LOOKUP_FIELD, fieldName, expectedData)));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field name");
            }
        } else if (section.equals("System Information")) {
            switch (fieldName) {
                case "Source System":
                    verifyElementVisible(By.xpath(String.format(LIGHTNING_TEXT_FIELD, fieldName, expectedData)));
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field name");
            }
        }
    }

    public void waitForLeadForwardedToDealer() {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMinutes(5))
                .pollingEvery(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Element not found within 5 minutes despite periodic refreshes");

        WebElement element = fluentWait.until(driver -> {
            // First, try to find the element WITHOUT refreshing
            try {
                WebElement elem = driver.findElement(
                        By.xpath("//td[@class='messageCell']//*[normalize-space(text())='Lead is in dealer responsibility and cannot be edited']")
                );
                if (elem.isDisplayed()) {
                    return elem; // Element found! Stop here, no refresh needed
                }
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                // Element not found, continue to refresh
            }

            // Element not found, so refresh the page
            driver.navigate().refresh();

            // Wait for page to stabilize after refresh
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            return null; // Continue waiting
        });

        Assert.assertTrue(element.isDisplayed());
    }

    public void verifyConsentStatusInConsentSummaryBoard(String consentName, String status) {

    }
}
