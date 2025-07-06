package pages;

import locators.elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyCategoryVisible(String categoryName){
        verifyElementVisible(By.xpath(getDynamicXpath(elements.ELEMENT_CATEGORY,categoryName)));
    }
}
