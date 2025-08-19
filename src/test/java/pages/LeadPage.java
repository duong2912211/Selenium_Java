package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static locators.elements.*;

public class LeadPage extends BasePage{

    public LeadPage(WebDriver driver) {
        super(driver);
    }

    public void clickQuickActionButtonOnLeadPage(String buttonName){
        By button = By.xpath(String.format(QUICK_ACTION_BUTTON,buttonName));
        verifyElementClickable(button);
        click(button);
    }

    public void verifyNewLeadTypeSelectFormVisible(){
        verifyElementVisible(By.xpath(String.format(NEW_LEAD_TYPE_SELECT_FORM_CHECKBOX,"Testdrive Lead")));
        verifyElementVisible(By.xpath(String.format(NEW_LEAD_TYPE_SELECT_FORM_CHECKBOX,"Fleet Lead")));
        verifyElementVisible(By.xpath(String.format(NEW_LEAD_TYPE_SELECT_FORM_CHECKBOX,"Unqualified Lead")));
        verifyElementVisible(By.xpath(String.format(NEW_LEAD_TYPE_SELECT_FORM_CHECKBOX,"User Chooser")));
    }

    public void clickOnNewLeadTypeCheckBox(String type){
        click(By.xpath(String.format(NEW_LEAD_TYPE_SELECT_FORM_CHECKBOX,type)));
    }

    public void clickOnButtonInNewLeadForm(String button){
        click(By.xpath(String.format(NEW_LEAD_FORM_BUTTON,button)));
    }

    public void verifyNewLeadCreationFormVisible(String formType){
        By formTitle = By.xpath(NEW_LEAD_CREATION_FORM_TITLE);
        verifyElementVisible(formTitle);
        Assert.assertEquals("New Lead: " + formType, getText(formTitle));

        verifyElementVisible(By.xpath(NEW_LEAD_CREATION_FORM));
    }
}
