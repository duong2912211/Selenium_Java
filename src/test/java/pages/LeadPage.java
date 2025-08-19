package pages;

import helper.ExcelHandler;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;

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

    public void updateNewLeadFormWithExcelData() throws IOException {
        List<String> dataList = ExcelHandler.readExcelFile();

        enterValueToInputField("firstName",dataList.get(0));
        enterValueToInputField("lastName",dataList.get(1));
        enterValueToInputField("MobilePhone",dataList.get(2));
        enterValueToInputField("Phone",dataList.get(3));
        enterValueToInputField("Email",dataList.get(4));
        enterValueToTextArea("street",dataList.get(5));
        enterValueToInputField("postalCode",dataList.get(6));
        enterValueToInputField("city",dataList.get(7));
    }
}
