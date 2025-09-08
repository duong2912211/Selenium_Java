package pages;

import helper.TestContext;
import locators.elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class formPage extends BasePage {

    public formPage(WebDriver driver) {
        super(driver);
    }

    public void verifyTestDriveTitle(String upperTitle, String lowerTitle){
        verifyElementVisible(By.xpath(String.format(elements.STEP_1_WEB_FORM_TITLE,upperTitle,lowerTitle)));
    }

    public void verifyTestDriveTitle(String title){
        verifyElementVisible(By.xpath(String.format(elements.STEP_2_WEB_FORM_TITLE,title)));
    }

    public void selectInterestVehicle(String interestVehicle){
        click(By.xpath(String.format(elements.INTEREST_VEHICLE_CHECKBOX,interestVehicle)));
    }

    public void enterValueToInputFieldInWebForm(String field){
        String fieldName = switch (field) {
            case "Vorname" -> "firstName";
            case "Nachname" -> "lastName";
            case "E-Mail" -> "email";
            case "Telefonnummer" -> "phone";
            default -> "";
        };

        WebElement inputField = driver.findElement(By.xpath(String.format(elements.INPUT_FIELD,fieldName,fieldName)));
        inputField.clear();
        inputField.click();
        inputField.sendKeys(getDataInJsonWithScenarioNumber(fieldName));


    }
}