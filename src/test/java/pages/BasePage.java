package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import runner.ConfigReader;

import java.time.Duration;

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

        String url = ConfigReader.get("base.url");
        driver.navigate().to(url+"/"+page);
    }

    public void navigateToHomePage(){
        String env = System.getProperty("env", "dev"); // default to 'dev'
        ConfigReader.load(env);

        String url = ConfigReader.get("base.url");
        System.out.println(url);
        driver.get(url);
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

    public void click(String xpath) {
        click(By.xpath(xpath));
    }

    public void type(By locator, String text) {
        waitForElementToBeVisible(locator).clear();
        waitForElementToBeVisible(locator).sendKeys(text);
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

    // Optional: scroll, JS click, etc.
    public void scrollToElement(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static String getDynamicXpath(String xpath, String value) {
        return String.format(xpath, value);
    }
}
