package pages;

import helper.Helper;
import helper.PhoneDE;
import helper.TestContext;
import helper.Timer;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import runner.ConfigReader;
import runner.Hooks;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;

import static locators.elements.*;
import static runner.Hooks.scenarioNumberialOrder;

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

    // <editor-fold desc="Common Action">
    public void navigateToPage(String page) {
        String env = System.getProperty("env", "dev"); // default to 'dev'
        ConfigReader.load(env);

        String url = ConfigReader.get("base." + page.replace(" ", "_").trim().toLowerCase());
        if (url == null) {
            throw new RuntimeException("No such parameter in config file");
        }
        driver.navigate().to(url);
    }

    /**
     * Waits until the page is fully loaded (document.readyState = "complete").
     */
    protected void waitForPageLoad() {
        wait.until(webDriver ->
                Objects.equals(((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState"), "complete"));
    }

    /**
     * Waits until the given element is visible on the page.
     *
     * @param locator By locator of the element
     * @return WebElement once visible
     */
    public WebElement waitForElementToBeVisible(By locator) {
        waitForPageLoad();
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the given element is clickable.
     *
     * @param locator By locator of the element
     * @return WebElement once clickable
     */
    public WebElement waitForElementToBeClickable(By locator) {
        waitForPageLoad();
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Waits until the given element disappears from the page (invisible).
     *
     * @param locator By locator of the element
     */
    public void waitForElementToDisappear(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    /**
     * Clicks on the element after waiting for it to be clickable.
     *
     * @param locator By locator of the element
     */
    public void click(By locator) {
        waitForElementToBeClickable(locator).click();
    }

    /**
     * Enters a value into an input field identified by field name.
     *
     * @param fieldName Field name used in locator
     * @param value     Value to enter
     */
    public void enterValueToInputField(String fieldName, String value) {
        By field = By.xpath(String.format(INPUT_FIELD, fieldName.trim(), fieldName.trim()));
        WebElement input = waitForElementToBeVisible(field);
        input.clear();
        input.sendKeys(value);
    }

    /**
     * Enters a value into a textarea field identified by field name.
     *
     * @param fieldName Field name used in locator
     * @param value     Value to enter
     */
    public void enterValueToTextArea(String fieldName, String value) {
        By field = By.xpath(String.format(TEXT_AREA, fieldName.trim(), fieldName.trim()));
        WebElement input = waitForElementToBeVisible(field);
        input.clear();
        input.sendKeys(value);
    }

    /**
     * Selects an option from a dropdown/select field by visible text.
     *
     * @param fieldName  Field name used in locator
     * @param optionText Option to select
     */
    public void selectOptionForSelectorField(String fieldName, String optionText) {
        By selectField = By.xpath(String.format(SELECT_FIELD, fieldName));
        scrollToElement(selectField);
        click(selectField);

        By option = By.xpath(String.format(SELECT_FIELD_OPTION, fieldName, optionText));
        click(option);
    }

    /**
     * Selects an option from a search-enabled field.
     *
     * @param fieldName Field name used in locator
     * @param data      Search value to input
     */
    public void selectOptionForSearchField(String fieldName, String data) {
        By field = By.xpath(String.format(NEW_LEAD_CREATION_SEARCH_FIELD, fieldName));
        WebElement vehicleInterestField = waitForElementToBeVisible(field);
        vehicleInterestField.clear();
        vehicleInterestField.sendKeys(data);

        By option = By.xpath(String.format(NEW_LEAD_CREATION_SEARCH_FIELD_OPTION, data));
        click(option);
    }

    /**
     * Clicks a button identified by its visible text.
     *
     * @param buttonText Text of the button
     */
    public void clickOnButtonWithText(String buttonText) {
        if(buttonText.equals("Absenden"))
        {
            Timer timer = new Timer();
            TestContext.addKeyToScenario(scenarioNumberialOrder,"submit time",timer.getCurrentTime());
        }
        click(By.xpath(String.format(BUTTON_WITH_TEXT, buttonText)));
    }

    /**
     * Clicks a button identified by its ID.
     *
     * @param buttonName Button ID
     */
    public void clickOnButtonWithId(String buttonName) {
        click(Helper.byIdIgnoreCaseAndSpaces("input", "id", buttonName));
    }

    /**
     * Gets the visible text of an element.
     *
     * @param locator By locator of the element
     * @return Text value of the element
     */
    public String getText(By locator) {
        return waitForElementToBeVisible(locator).getText();
    }

    /**
     * Checks if an element is visible.
     *
     * @param locator By locator of the element
     *                assert true if visible, false otherwise
     */
    public void verifyElementVisible(By locator) {
        Assert.assertTrue(waitForElementToBeVisible(locator).isDisplayed());
    }

    /**
     * Checks if an element is clickable.
     *
     * @param locator By locator of the element
     *                Assert true if clickable, false otherwise
     */
    public void verifyElementClickable(By locator) {
        Assert.assertTrue(waitForElementToBeClickable(locator).isDisplayed());
    }

    /**
     * Verifies that an input field is visible.
     *
     * @param fieldName Field name used in locator
     */
    public void verifyInputFieldIsVisible(String fieldName) {
        verifyElementVisible(By.xpath(String.format(INPUT_FIELD, Helper.convertTextToId(fieldName), Helper.convertTextToId(fieldName))));
    }

    /**
     * Verifies that the error message is visible and matches the expected text.
     *
     * @param errorMsg Expected error message
     */
    public void verifyErrorMessageIsVisible(String errorMsg) {
        verifyElementVisible(By.xpath(ERROR_MSG_WITH_ID));
        Assert.assertEquals(getText(By.xpath(ERROR_MSG_WITH_ID)), errorMsg);
    }

    /**
     * Verifies that an input field is clickable and not blocked.
     *
     * @param fieldName Field name used in locator
     */
    public void verifyInputFieldIsClickable(String fieldName) {
        By field = By.xpath(String.format(INPUT_FIELD, Helper.convertTextToId(fieldName), Helper.convertTextToId(fieldName)));
        verifyElementClickable(field);
        Helper.checkElementNotBlocked(driver, field);
    }

    /**
     * Verifies that a button is visible by its ID.
     *
     * @param buttonName Button ID
     */
    public void verifyButtonIsVisible(String buttonName) {
        verifyElementVisible(By.xpath(String.format(BUTTON_WITH_ID, buttonName)));
    }

    /**
     * Scrolls the page until the element is in view.
     *
     * @param locator By locator of the element
     */
    public void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center', behavior: 'auto'});", element);
    }

    public void openNewTab() {
        // Open new tab
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open();");

        // Switch to the new tab
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.getLast()); // Switch to second tab (index 1)
    }

    public void switchToTab(WebDriver driver, int tabIndex) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        if (tabIndex < tabs.size()) {
            driver.switchTo().window(tabs.get(tabIndex));
        }
    }
    // </editor-fold>

    // <editor-fold desc="Saleforce Action">

    /**
     * Verifies that the application name is visible and correct.
     *
     * @param appName Expected application name
     */
    public void verifyAppNameIsVisible(String appName) {
        By appTitles = By.xpath(String.format(APP_TITLES, appName));
        verifyElementVisible(appTitles);
        Assert.assertEquals(getText(appTitles), appName);
    }

    /**
     * Verifies that a tab is visible and correct.
     *
     * @param tabName Expected tab name
     */
    public void verifyTabIsVisible(String tabName) {
        By appTitles = By.xpath(String.format(TAB_WITH_TEXT, tabName));
        verifyElementVisible(appTitles);
        Assert.assertEquals(getText(appTitles), tabName);
    }

    public void switchFilterWithName(String filterName) {
        click(By.xpath(FILTER_LIST));
        click(By.xpath(String.format(FILTER_OPTION_WITH_TEXT, filterName)));
        Helper.pause(3);
    }

    /**
     * Clicks the navigation menu icon.
     */
    public void clickOnShowNavigationMenuIcon() {
        click(By.xpath(SHOW_NAVIGATION_MENU));
    }

    /**
     * Selects an item from the navigation menu.
     *
     * @param menuItem Menu item text
     */
    public void selectNavigationMenu(String menuItem) {
        click(By.xpath(String.format(NAVIGATION_MENU_WITH_TEXT, menuItem)));
    }

    /**
     * Clicks a quick action button by name.
     *
     * @param buttonName Button text
     */
    public void clickQuickActionButton(String buttonName) {
        By button = By.xpath(String.format(QUICK_ACTION_BUTTON, buttonName));
        verifyElementClickable(button);
        click(button);
    }

    /**
     * Clicks a checkbox in the new record type selection form.
     *
     * @param type Record type
     */
    public void clickOnNewRecordTypeSelectCheckBox(String type) {
        click(By.xpath(String.format(NEW_RECORD_TYPE_SELECT_FORM_CHECKBOX, type)));
    }

    /**
     * Clicks a button in the new record type selection form.
     *
     * @param button Button text
     */
    public void clickOnButtonInNewRecordSelectTypeForm(String button) {
        click(By.xpath(String.format(NEW_RECORD_TYPE_SELECT_FORM_BUTTON, button)));
    }

    public void searchRecordWithName(String saleforceObject, String recordName) {
        enterValueToInputField(saleforceObject + "-search-input", recordName);
        pressEnter(saleforceObject + "-search-input");
        Helper.pause(3);
    }

    public void clickOnRecordOnListingTable(String recordName) {
        click(By.xpath(String.format(LISTING_TABLE_RECORD_NAME, recordName)));
    }

    public void verifyRecordTitle(String recordType, String recordName) {
        var recordTypeLabel = driver.findElements(By.xpath(RECORD_TITLES_TYPE));

        WebElement visibleRecordTypeLabel = recordTypeLabel.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElse(null);

        if (visibleRecordTypeLabel != null) {
            System.out.println(visibleRecordTypeLabel.getText());
            Assert.assertEquals(recordType, visibleRecordTypeLabel.getText());
        }

        var recordNameLabel = driver.findElements(By.xpath(RECORD_TITLES_NAME));

        WebElement visibleRecordNameLabel = recordNameLabel.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElse(null);

        if (visibleRecordNameLabel != null) {
            System.out.println(visibleRecordNameLabel.getText());
            Assert.assertEquals(recordName, visibleRecordNameLabel.getText());
        }
    }

    public void verifySecondaryFieldData(String secondaryFieldTitle) {
        String jsonFieldName;
        String elementXpath = "";

        switch (secondaryFieldTitle) {
            case "Phone":
                jsonFieldName = "phone";
                System.out.println(PhoneDE.toE164(getDataInJsonWithScenarioNumber(jsonFieldName)));
                if (!Objects.equals(driver.findElement(By.xpath(PHONE_EXPAND_BTN)).getAttribute("aria-expanded"), "true")) {
                    driver.findElement(By.xpath(PHONE_EXPAND_BTN)).click();
                }
                elementXpath = String.format(RECORD_HIGHLIGHTED_DETAILS_PHONE, PhoneDE.toE164(getDataInJsonWithScenarioNumber(jsonFieldName)));
                break;
            case "Mobile":
                jsonFieldName = "mobile";
                System.out.println(PhoneDE.toE164(getDataInJsonWithScenarioNumber(jsonFieldName)));
                if (!Objects.equals(driver.findElement(By.xpath(PHONE_EXPAND_BTN)).getAttribute("aria-expanded"), "true")) {
                    driver.findElement(By.xpath(PHONE_EXPAND_BTN)).click();
                }
                elementXpath = String.format(RECORD_HIGHLIGHTED_DETAILS_MOBILE, PhoneDE.toE164(getDataInJsonWithScenarioNumber(jsonFieldName)));
                break;
            case "Email":
                jsonFieldName = "email --> nomail.com";
                elementXpath = String.format(RECORD_HIGHLIGHTED_DETAILS_EMAIL, getDataInJsonWithScenarioNumber(jsonFieldName));
                break;
            case "Vehicle Interest":
                jsonFieldName = "vehicle interest";
                elementXpath = String.format(RECORD_HIGHLIGHTED_DETAILS_VEHICLE, getDataInJsonWithScenarioNumber(jsonFieldName));
                break;
            case "Dealer IM":
                jsonFieldName = "test dealer";
                elementXpath = String.format(RECORD_HIGHLIGHTED_DETAILS_DEALER, getDataInJsonWithScenarioNumber(jsonFieldName));
                break;
            default:
                elementXpath = "";
        }
        verifyElementVisible(By.xpath(elementXpath));
    }

    public void clickOnTabInRecordPage(String tabName) {
        scrollToElement(By.xpath(String.format(TAB_IN_RECORD_PAGE, tabName)));
        var tabInRecordPage = driver.findElements(By.xpath(String.format(TAB_IN_RECORD_PAGE, tabName)));

        WebElement visibleRecordNameLabel = tabInRecordPage.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .orElse(null);

        assert visibleRecordNameLabel != null;
        visibleRecordNameLabel.click();
    }

    public void verifyTabSelectedInRecordPage(String tabName) {
        WebElement activeTab = driver.findElement(By.xpath(String.format(TAB_IN_RECORD_PAGE, tabName)));
        Assert.assertTrue("Tab" + tabName + "is not selected", Objects.requireNonNull(activeTab.getAttribute("class")).contains("active"));
    }

    // </editor-fold>

    /**
     * Presses Enter key in a specific input field.
     *
     * @param fieldName Field name used in locator
     */
    public void pressEnter(String fieldName) {
        By field = By.xpath(String.format(INPUT_FIELD, fieldName.trim(), fieldName.trim()));
        WebElement input = waitForElementToBeVisible(field);
        input.sendKeys(Keys.ENTER);
    }

    /**
     * Retrieves data from JSON test context by scenario number.
     *
     * @param key Key to look up
     * @return Value from JSON
     */
    public String getDataInJsonWithScenarioNumber(String key) {
        JSONObject dataList = TestContext.getAllData();
        return dataList.getJSONObject(Hooks.scenarioNumberialOrder).getString(key);
    }

    public void waitForBackendProcessCompletion(){
        Instant startTime = Instant.now();
        AtomicReference<Instant> lastRefreshTime = new AtomicReference<>(startTime);

        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofMinutes(5))           // Total timeout: 5 minutes
                .pollingEvery(Duration.ofSeconds(10))         // Check every 10 seconds
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class)
                .withMessage("Element not found within 5 minutes despite periodic refreshes");

        WebElement element = fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                Instant currentTime = Instant.now();
                long elapsedSeconds = Duration.between(startTime, currentTime).getSeconds();
                long timeSinceLastRefresh = Duration.between(lastRefreshTime.get(), currentTime).getSeconds();

                System.out.printf("\uD83D\uDD0D Checking elements... Elapsed: %ds | Since last refresh: %ds%n",
                        elapsedSeconds, timeSinceLastRefresh);

                // Refresh every minute (60 seconds)
                if (timeSinceLastRefresh >= 10) {
                    System.out.println("🔄 Refreshing page - 10s elapsed since last refresh");
                    click(By.xpath("(//lightning-button-icon//button[@title='Refresh'])[1]"));
                    lastRefreshTime.set(Instant.now());

                    // Wait a bit for page to load after refresh
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }

                // Check for the target element
                try {
                    WebElement targetElement = driver.findElement(By.xpath(String.format(LISTING_TABLE_RECORD_NAME,getDataInJsonWithScenarioNumber("firstname") + " " + getDataInJsonWithScenarioNumber("lastname"))));
                    if (targetElement.isDisplayed()) {
                        System.out.println("✅ Target element found and visible!");
                        return targetElement;
                    }
                } catch (NoSuchElementException e) {
                    // Element not found, continue waiting
                }

                return null; // Continue waiting
            }
        });

        Assert.assertTrue(element.isDisplayed());
    }

    public void verifyAndClickOnPresentationTab(String recordName,String recordType){
        click(By.xpath(String.format(PRESENTATION_TAB,recordName,recordType)));
    }
}