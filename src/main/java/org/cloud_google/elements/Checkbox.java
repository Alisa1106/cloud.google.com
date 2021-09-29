package org.cloud_google.elements;

import lombok.extern.log4j.Log4j;
import org.cloud_google.utils.JsAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j
public class Checkbox {

    WebDriver driver;
    String label;
    JsAction jsAction;

    public Checkbox(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
        jsAction = new JsAction(driver);
    }

    public static final String CHECKBOX_XPATH = "//*[@role='checkbox']//*[contains(text(),'%s')]";

    public void mark() {
        log.info(String.format("Mark checkbox: '%s'", label));
        jsAction.clickByJs(driver.findElement(By.xpath(String.format(CHECKBOX_XPATH, label))));
    }
}