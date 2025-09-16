package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static locators.elements.*;

public class accountPage extends BasePage{

    public accountPage(WebDriver driver) {
        super(driver);
    }

    public void verifyNewAccountTypeSelectFormVisible(){
        verifyElementVisible(By.xpath(String.format(NEW_RECORD_TYPE_SELECT_FORM_CHECKBOX, "Person Account")));
        verifyElementVisible(By.xpath(String.format(NEW_RECORD_TYPE_SELECT_FORM_CHECKBOX, "Business Account")));
    }

    public void verifyNewAccountFormLoaded(String type){
        By formTitle = By.xpath(NEW_LEAD_CREATION_FORM_TITLE);
        verifyElementVisible(formTitle);
        Assert.assertEquals("New Lead: " + type, getText(formTitle));

        verifyElementVisible(By.xpath(NEW_RECORD_CREATION_FORM));
    }
}
