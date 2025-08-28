package pages;

import helper.ExcelHandler;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.Hooks;

import java.io.IOException;
import java.rmi.NoSuchObjectException;
import java.util.*;

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

    public void clickOnNewLeadTypeSelectCheckBox(String type){
        click(By.xpath(String.format(NEW_LEAD_TYPE_SELECT_FORM_CHECKBOX,type)));
    }

    public void clickOnButtonInNewLeadSelectTypeForm(String button){
        click(By.xpath(String.format(NEW_LEAD_TYPE_SELECT_FORM_BUTTON,button)));
    }

    public void clickOnButtonInNewLeadForm(String buttonText){
        String button = switch (buttonText) {
            case "Save" -> "SaveEdit";
            case "Save & New" -> "SaveAndNew";
            case "Cancel" -> "CancelEdit";
            default -> "";
        };
        click(By.xpath(String.format(NEW_LEAD_FORM_BUTTON,button)));
    }

    public void verifyNewLeadCreationFormVisible(String formType){
        By formTitle = By.xpath(NEW_LEAD_CREATION_FORM_TITLE);
        verifyElementVisible(formTitle);
        Assert.assertEquals("New Lead: " + formType, getText(formTitle));

        verifyElementVisible(By.xpath(NEW_LEAD_CREATION_FORM));
    }

    public void updateNewLeadFormWithExcelData() throws IOException {
        HashMap<String,String> dataList = ExcelHandler.readExcelFile();


        enterValueToInputField("firstName",dataList.get("firstname"));
        enterValueToInputField("lastName",dataList.get("lastname"));
        enterValueToInputField("MobilePhone",dataList.get("mobile"));
        enterValueToInputField("Phone",dataList.get("phone"));
        enterValueToInputField("Email",dataList.get("email --> nomail.com"));
        enterValueToTextArea("street",dataList.get("street"));
        enterValueToInputField("postalCode",dataList.get("zip"));
        enterValueToInputField("city",dataList.get("city"));
        selectOptionForSelectorField("Customer Type","Test Drive");
    }

    public void verifyNewRecordShowOnLeadListingPage() throws IOException, InterruptedException {
        //Read the current data used to create Leads
        HashMap<String,String> dataList = ExcelHandler.readExcelFile();
        //Get the expected Full Name of Leads
        String expectedName = dataList.get("firstname") + " " + dataList.get("lastname");

        //Switch filter back to Test Latest Leads
        click(By.xpath(FILTER_LIST));
        click(By.xpath(String.format(FILTER_OPTION_WITH_TEXT,"Test Latest Leads")));
        Thread.sleep(3000);

        //Search for new lead record name
        enterValueToInputField("Lead-search-input",expectedName);
        pressEnter("Lead-search-input");
        Thread.sleep(3000);

        //Verify number and records match with new lead full name
        By leadListingTable = By.xpath(LEAD_LISTING_TABLE);

        if(verifyElementVisible(leadListingTable)){
            //Get the maximum records on the listing page
            String rowNumber = driver.findElement(leadListingTable).getAttribute("data-num-rows");
            for (int i= 1; i <= Integer.parseInt(Objects.requireNonNull(rowNumber)) ; i++){
                String rowXpath = "//tr[@data-row-number='"+i+"']";
                Assert.assertEquals(expectedName,driver.findElement(By.xpath(rowXpath + "//th[@data-label='Name']//slot/span")).getText());
                Assert.assertEquals("+49 " + dataList.get("mobile").replaceAll("[()]", "").substring(1),driver.findElement(By.xpath(rowXpath + "//td[@data-label='Mobile']//a")).getText());
                Assert.assertEquals("+49 " + dataList.get("phone").replaceAll("[()]", "").substring(1),driver.findElement(By.xpath(rowXpath + "//td[@data-label='Phone']//a")).getText());
                Assert.assertEquals("SEAT",driver.findElement(By.xpath(rowXpath + "//td[@data-label='Brand']//lst-formatted-text/span")).getText());
            }

        } else throw new RuntimeException("No record found");
    }
}
