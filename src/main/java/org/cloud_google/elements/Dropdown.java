package org.cloud_google.elements;

import lombok.extern.log4j.Log4j;
import org.cloud_google.utils.JsAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j
public class Dropdown {

    WebDriver driver;
    String label;
    JsAction jsAction;

    public Dropdown(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
        jsAction = new JsAction(driver);
    }

    public static final String DROPDOWN_XPATH = "//*[text()='%s']/ancestor::*[@class='layout-row']";
    public static final String SELECT_OPTION_XPATH = "//*[contains(@class,'md-text')][contains(text(),'%s')]";

    public void select(String option) {
        log.info(String.format("Select '%s' in '%s' dropdown.", option, label));
        jsAction.clickByJs(driver.findElement(By.xpath(String.format(DROPDOWN_XPATH, label))));
        jsAction.clickByJs(driver.findElement(By.xpath(String.format(SELECT_OPTION_XPATH, option))));
    }
}