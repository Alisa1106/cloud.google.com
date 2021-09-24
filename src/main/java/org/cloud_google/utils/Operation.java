package org.cloud_google.utils;

import org.cloud_google.elements.Button;
import org.cloud_google.elements.Checkbox;
import org.cloud_google.elements.Dropdown;
import org.cloud_google.elements.Input;
import org.openqa.selenium.WebDriver;

public class Operation {

    WebDriver driver;

    public Operation(WebDriver driver) {
        this.driver = driver;
    }

    public void fillInputField(String fieldName, String text) {
        new Input(driver, fieldName).writeText(text);
    }

    public void selectDropdownOption(String fieldName, String optionName) {
        new Dropdown(driver, fieldName).select(optionName);
    }

    public void markCheckbox(String checkboxName) {
        new Checkbox(driver, checkboxName).mark();
    }

    public void clickButton(String buttonName) {
        new Button(driver, buttonName).click();
    }
}