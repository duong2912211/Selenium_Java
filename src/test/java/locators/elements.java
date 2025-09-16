package locators;

public class elements {
    //SaleForce Base
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
    public static final String QUICK_ACTION_BUTTON = "//a[@class='forceActionLink' and @title='%s']";
    public static final String NEW_RECORD_TYPE_SELECT_FORM_CHECKBOX = "(//span[text()='%s'])[1]";
    public static final String NEW_RECORD_TYPE_SELECT_FORM_BUTTON = "//button[@data-aura-class='uiButton']/span[text()='%s']";
    public static final String NEW_RECORD_CREATION_FORM = "//records-base-record-form";

    public static final String RECORD_TITLES_TYPE = "//slot[@name='entityLabel']/records-entity-label";
    public static final String RECORD_TITLES_NAME= "//slot[@name='primaryField']/lightning-formatted-name";
    public static final String RECORD_SECONDARY_FIELD = "//slot[@name='secondaryFields']//p[@title='%s']/following-sibling::p";

    //Home
    public static final String APP_TITLES = "//span[@class='slds-truncate' and @title='%s']";
    public static final String TAB_WITH_TEXT = "//span[@class='slds-truncate' and text()='%s']";
    public static final String MENU = "//span[text()='%s']/ancestor::li[contains(@id,'item')]";

    //Login
    public static final String FORGOT_YOUR_PASSWORD_LINK = "//a[@id='forgot_password_link']";

    //Leads
    public static final String RECORD_LISTING_TABLE = "//table[contains(@class,'slds-table')]";
    public static final String NEW_LEAD_FORM_BUTTON = " //button[@name='%s']";
    public static final String NEW_LEAD_CREATION_FORM_TITLE = "//h2[contains(@class,'slds-modal__title')]";
    public static final String NEW_LEAD_CREATION_SEARCH_FIELD = "//label[text()='%s']/following-sibling::div//input";
    public static final String NEW_LEAD_CREATION_SEARCH_FIELD_OPTION = "//lightning-base-combobox-formatted-text[@title='%s']";

    //Webform
    public static final String WEB_FORM_TITLE = "//h1[contains(text(),'%s')]";
    public static final String INTEREST_VEHICLE_CHECKBOX = "//label[normalize-space(text())='%s']/following-sibling::button[@role='radio']";
    public static final String CAR_SELECTED_NAME = "//div[contains(@class,'current-car')]//span[@class='model-name']";
    public static final String WEB_FORM_INPUT_FIELD = "(//label[normalize-space(text())='%s']/following-sibling::input)[1]";
    public static final String SEAT_PARTNER_ADDRESS_SEARCH_FIELD = "//input[@placeholder='PLZ, Stadt oder Name']";
    public static final String SEAT_PARTNER_ADDRESS_SEARCH_OPTION = "//div[@role='listbox']//div[normalize-space(text())='%s']";
    public static final String SEAT_PARTNER_OPTION = "//div[@role='radiogroup']//label[.//h1[normalize-space(.)='%s']]";
    public static final String WEB_FORM_CONSENT_CHECK_BOX = "//label[text()='%s']/../preceding-sibling::button[@role='checkbox']";
}
