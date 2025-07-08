package pages;

import helper.JsonDataReader;
import io.cucumber.java.Scenario;
import locators.elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import runner.Hooks;

import static locators.elements.*;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void verifyCategoryVisible(String categoryName){
        verifyElementVisible(By.xpath(String.format(ELEMENT_CATEGORY, categoryName)));
    }

    public void clickOnCategoryMenu (String categoryName){
        By category = By.xpath(String.format(ELEMENT_CATEGORY, categoryName));
        scrollToElement(category);
        click(category);
    }

    public void verifyMenuListExtended(String categoryName){
        String menuList = JsonDataReader.get(Hooks.getScenarioPrefix(),categoryName);
        String[] menuName = menuList.split(",");
        for (String menu : menuName) {
            verifyElementVisible(By.xpath(String.format(MENU,menu)));
            verifyElementClickable(By.xpath(String.format(MENU,menu)));
        }
    }

}
