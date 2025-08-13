package pages;

import helper.Helper;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runner.ConfigReader;

import java.time.Duration;

import static locators.elements.*;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Common Actions
    public void navigateToPage(String page){
        String env = System.getProperty("env", "dev"); // default to 'dev'
        ConfigReader.load(env);

        String url = ConfigReader.get("base.home_url");
        if(url == null){
            throw new RuntimeException("No such parameter in config file");
        }
        String customUrl = "";
        if(page.isBlank()){
            customUrl = url;
        }else {
            customUrl = url+"/"+page;
        }
        driver.navigate().to(customUrl);
    }

    public WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void click(By locator) {
        waitForElementToBeClickable(locator).click();
    }

    public void enterValueToInputField(String fieldName, String value){
        By field = By.xpath(String.format(INPUT_FIELD_WITH_ID, fieldName.trim()));
        waitForElementToBeVisible(field).clear();
        waitForElementToBeVisible(field).sendKeys(value);
    }

    public void enterValueToTextArea(String fieldName, String value){
        By field = By.xpath(String.format(TEXT_AREA_WITH_ID, fieldName.trim()));
        waitForElementToBeVisible(field).clear();
        waitForElementToBeVisible(field).sendKeys(value);
    }

    public void clickOnButtonWithName(String buttonName){
        click(By.xpath(String.format(BUTTON_WITH_TEXT,buttonName)));
    }

    public String getText(By locator) {
        return waitForElementToBeVisible(locator).getText();
    }

    public boolean verifyElementVisible(By locator) {
        try {
            return waitForElementToBeVisible(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean verifyElementClickable(By locator) {
        try {
            return waitForElementToBeClickable(locator).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void verifyInputFieldIsVisible(String fieldName){
        verifyElementVisible(By.xpath(String.format(INPUT_FIELD_WITH_ID,Helper.convertTextToId(fieldName))));
    }

    public void verifyErrorMessageIsVisible(String errorMsg){
        verifyElementVisible(By.xpath(ERROR_MSG_WITH_ID));
        System.out.println(getText(By.xpath(ERROR_MSG_WITH_ID)));
        Assert.assertEquals(getText(By.xpath(ERROR_MSG_WITH_ID)),errorMsg);
    }

    public void verifyInputFieldIsClickable(String fieldName){
        By field = By.xpath(String.format(INPUT_FIELD_WITH_ID,Helper.convertTextToId(fieldName)));
        verifyElementClickable(field);
        Helper.checkElementNotBlocked(driver,field);
    }

    // Optional: scroll, JS click, etc.
    public void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
