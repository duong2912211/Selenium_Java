package locators;

import org.openqa.selenium.By;

public class elements {
    //Base
    public static final String INPUT_FIELD_WITH_ID = "//input[@id='%s']";
    public static final String TEXT_AREA_WITH_ID = "//textarea[@id='%s']";
    public static final String BUTTON_WITH_ID = "//button[@id='%s']";
    public static final String BUTTON_WITH_TEXT = "//button[text()='%s']";
    public static final String ERROR_MSG_WITH_ID = "//input[@id='error']";
    //Home
    public static final String ELEMENT_CATEGORY = "//h5[text()='%s']/ancestor::div[contains(@class,'top-card')]";
    public static final String MENU = "//span[text()='%s']/ancestor::li[contains(@id,'item')]";
    //TextBox

}
