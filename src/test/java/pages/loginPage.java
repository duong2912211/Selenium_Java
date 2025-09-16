package pages;

import locators.elements;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginPage extends BasePage {

    public loginPage(WebDriver driver) {
        super(driver);
    }

    public void verifyLinksIsVisible(String linkPlaceholderText) {
        By forgotPasswordLink = By.xpath(elements.FORGOT_YOUR_PASSWORD_LINK);
        verifyElementVisible(forgotPasswordLink);
        Assert.assertEquals(getText(forgotPasswordLink), linkPlaceholderText);
    }
}