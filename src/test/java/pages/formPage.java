package pages;

import locators.elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

public class formPage extends BasePage {

    public formPage(WebDriver driver) {
        super(driver);
    }

    // --- Title ---
    public void verifyTestDriveTitle(String title) {
        verifyElementVisible(By.xpath(String.format(elements.WEB_FORM_TITLE, title)));
    }

    // --- Vehicle selection ---
    public void selectInterestVehicle(String interestVehicle) {
        click(By.xpath(String.format(elements.INTEREST_VEHICLE_CHECKBOX, interestVehicle)));
    }

    // --- Input fields ---
    public void enterValueToInputFieldInWebForm(String field) {
        // map German label -> JSON key
        String fieldName = switch (field) {
            case "Vorname" -> "firstname";
            case "Nachname" -> "lastname";
            case "E-Mail" -> "email";
            case "Telefonnummer" -> "phone";
            default -> throw new IllegalArgumentException("Unknown field: " + field);
        };

        WebElement inputField = driver.findElement(
                By.xpath(String.format(elements.WEB_FORM_INPUT_FIELD, field))
        );

        if(fieldName.equals("email")){
            fieldName = "email --> nomail.com";
        }

        inputField.clear();
        inputField.click();
        System.out.println(getDataInJsonWithScenarioNumber(fieldName));
        inputField.sendKeys(getDataInJsonWithScenarioNumber(fieldName));
    }

    // --- SEAT Partner search ---
    public void searchSeatPartner(String query) {
        WebElement searchField = driver.findElement(By.xpath(elements.SEAT_PARTNER_ADDRESS_SEARCH_FIELD));
        searchField.clear();
        searchField.sendKeys(query);
    }

    public void selectSeatPartnerFromList(String optionText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format(elements.SEAT_PARTNER_ADDRESS_SEARCH_OPTION, optionText))
        ));
        option.click();
    }

    // --- SEAT Partner carousel ---
    public void selectSeatPartnerFromCarousel(String dealerName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement dealerCard = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(String.format(elements.SEAT_PARTNER_OPTION, dealerName))
        ));
        dealerCard.click();
    }

    // --- Consent checkboxes ---
    public void tickConsentCheckbox(String consentType) {
        String xpath = String.format(elements.WEB_FORM_CONSENT_CHECK_BOX,consentType);
        WebElement checkbox = driver.findElement(By.xpath(xpath));
        if (Objects.equals(checkbox.getAttribute("data-state"), "unchecked")) {
            checkbox.click();
        }
    }

    // --- Verification ---
    public void verifyCurrentCar(String expectedCar) {
        String actualCar = driver.findElement(By.xpath(elements.CAR_SELECTED_NAME)).getText().trim();
        if (!actualCar.equals(expectedCar)) {
            throw new AssertionError("Expected: " + expectedCar + " but found: " + actualCar);
        }
    }
}
