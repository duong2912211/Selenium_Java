package locators;

public class elements {
    //Base
    public static final String INPUT_FIELD = "//input[@id='%s' or @name='%s']";
    public static final String TEXT_AREA = "//textarea[@id='%s' or @name='%s']";
    public static final String SELECT_FIELD = "//lightning-base-combobox//button[@aria-label='%s']";
    public static final String SELECT_FIELD_OPTION = "//div[@aria-label='%s']/lightning-base-combobox-item[@data-value='%s']";
    public static final String BUTTON_WITH_ID = "//button[@id='%s']";
    public static final String BUTTON_WITH_TEXT = "//button[text()='%s']";
    public static final String ERROR_MSG_WITH_ID = "//div[@id='error']";
    public static final String SHOW_NAVIGATION_MENU = "//button[@title='Show Navigation Menu']";
    public static final String NAVIGATION_MENU_WITH_TEXT = "//a[@data-label='%s']";
    public static final String FILTER_LIST = "//h1[contains(@class,'page-header__name-title')]/span[contains(@class,'page-header__title')]";
    public static final String FILTER_OPTION_WITH_TEXT = "//lightning-base-combobox-item//span[text()='%s']";
    //Home
    public static final String APP_TITLES = "//span[@class='slds-truncate' and @title='%s']";
    public static final String TAB_WITH_TEXT = "//span[@class='slds-truncate' and text()='%s']";
    public static final String MENU = "//span[text()='%s']/ancestor::li[contains(@id,'item')]";
    //Login
    public static final String FORGOT_YOUR_PASSWORD_LINK = "//a[@id='forgot_password_link']";

    //Leads
    public static final String QUICK_ACTION_BUTTON = "//a[@class='forceActionLink' and @title='%s']";
    public static final String LEAD_LISTING_TABLE = "//table[@aria-label='Test Latest Leads']";
    public static final String NEW_LEAD_TYPE_SELECT_FORM_CHECKBOX = "(//span[text()='%s'])[1]";
    public static final String NEW_LEAD_TYPE_SELECT_FORM_BUTTON = "//button[@data-aura-class='uiButton']/span[text()='%s']";
    public static final String NEW_LEAD_FORM_BUTTON = " //button[@name='%s']";
    public static final String NEW_LEAD_CREATION_FORM_TITLE = "//h2[contains(@class,'slds-modal__title')]";
    public static final String NEW_LEAD_CREATION_FORM = "//records-base-record-form";
    public static final String NEW_LEAD_CREATION_SEARCH_FIELD = "//label[text()='%s']/following-sibling::div//input";
    public static final String NEW_LEAD_CREATION_SEARCH_FIELD_OPTION = "//lightning-base-combobox-formatted-text[@title='%s']";
}
