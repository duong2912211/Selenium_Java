package locators;

public class elements {
    //Base
    public static final String INPUT_FIELD_WITH_ID = "//input[@id='%s']";
    public static final String TEXT_AREA_WITH_ID = "//textarea[@id='%s']";
    public static final String BUTTON_WITH_ID = "//button[@id='%s']";
    public static final String BUTTON_WITH_TEXT = "//button[text()='%s']";
    public static final String ERROR_MSG_WITH_ID = "//div[@id='error']";
    //Home
    public static final String APP_TITLES = "//span[@class='slds-truncate' and @title='%s']";
    public static final String TAB_WITH_TEXT = "//span[@class='slds-truncate' and text()='%s']";
    public static final String MENU = "//span[text()='%s']/ancestor::li[contains(@id,'item')]";
    //Login
    public static final String FORGOT_YOUR_PASSWORD_LINK = "//a[@id='forgot_password_link']";

    //Leads
    public static final String QUICK_ACTION_BUTTON = "//a[@class='forceActionLink' and @title='%s'] ";
    public static final String NEW_LEAD_TYPE_SELECT_FORM_CHECKBOX = "(//span[text()='%s'])[1]";
    public static final String NEW_LEAD_FORM_BUTTON = "//button[@data-aura-class='uiButton']/span[text()='%s']";
    public static final String NEW_LEAD_CREATION_FORM_TITLE = "//h2[contains(@class,'slds-modal__title')]";
    public static final String NEW_LEAD_CREATION_FORM = "//records-base-record-form";
}
