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

    public static BasePage createGenericPage(WebDriver driver) {
        return new BasePage(driver) {
            // Anonymous class implementation
            // This creates a concrete instance of the abstract class
        };
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

    /**
     * Wait for page to load completely
     */
    protected void waitForPageLoad() {
        wait.until(webDriver ->
                ((org.openqa.selenium.JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    public WebElement waitForElementToBeVisible(By locator) {
        waitForPageLoad();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementToBeClickable(By locator) {
        waitForPageLoad();
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void click(By locator) {
        waitForElementToBeClickable(locator).click();
    }

    public void enterValueToInputField(String fieldName, String value){
        By field = By.xpath(String.format(INPUT_FIELD, fieldName.trim(),fieldName.trim()));
        WebElement input = waitForElementToBeVisible(field);
        input.clear();
        input.sendKeys(value);
    }

    public void enterValueToTextArea(String fieldName, String value){
        By field = By.xpath(String.format(TEXT_AREA, fieldName.trim(), fieldName.trim()));
        WebElement input = waitForElementToBeVisible(field);
        input.clear();
        input.sendKeys(value);
    }

    public void selectOptionForSelectorField(String fieldName, String optionText){
        By selectField = By.xpath(String.format(SELECT_FIELD,fieldName));
        click(selectField);

        By option = By.xpath(String.format(SELECT_FIELD_OPTION,fieldName,optionText));
        click(option);
    }

    public void selectOptionForSearchField(String fieldName,String data){
        By field = By.xpath(String.format(NEW_LEAD_CREATION_SEARCH_FIELD,fieldName));
        WebElement vehicleInterestField = waitForElementToBeVisible(field);
        vehicleInterestField.clear();
        vehicleInterestField.sendKeys(data);

        By option = By.xpath(String.format(NEW_LEAD_CREATION_SEARCH_FIELD_OPTION,data));
        click(option);
    }

    public void clickOnButtonWithName(String buttonName){
        click(By.xpath(String.format(BUTTON_WITH_TEXT,buttonName)));
    }

    public void clickOnButtonWithId(String buttonName){
        click(Helper.byIdIgnoreCaseAndSpaces("input","id",buttonName));
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
        verifyElementVisible(By.xpath(String.format(INPUT_FIELD,Helper.convertTextToId(fieldName))));
    }

    public void verifyErrorMessageIsVisible(String errorMsg){
        verifyElementVisible(By.xpath(ERROR_MSG_WITH_ID));
        Assert.assertEquals(getText(By.xpath(ERROR_MSG_WITH_ID)),errorMsg);
    }

    public void verifyInputFieldIsClickable(String fieldName){
        By field = By.xpath(String.format(INPUT_FIELD,Helper.convertTextToId(fieldName)));
        verifyElementClickable(field);
        Helper.checkElementNotBlocked(driver,field);
    }

    public void verifyButtonIsVisible(String buttonName){
        verifyElementVisible(By.xpath(String.format(BUTTON_WITH_ID,buttonName)));
    }

    // Optional: scroll, JS click, etc.
    public void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void verifyAppNameIsVisible(String appName){
        By appTitles = By.xpath(String.format(APP_TITLES, appName));
        verifyElementVisible(appTitles);
        Assert.assertEquals(getText(appTitles),appName);
    }

    public void verifyTabIsVisible(String tabName){
        By appTitles = By.xpath(String.format(TAB_WITH_TEXT, tabName));
        verifyElementVisible(appTitles);
        Assert.assertEquals(getText(appTitles),tabName);
    }
}