package org.cloud_google.elements;

import lombok.extern.log4j.Log4j;
import org.cloud_google.utils.JsAction;
import org.cloud_google.utils.Waiter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j
public class Button {

    WebDriver driver;
    String label;
    Waiter waiter;
    JsAction jsAction;

    public Button(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
        waiter = new Waiter(driver);
        jsAction = new JsAction(driver);
    }

    public static final String BUTTON_XPATH = "//*[contains(@class,'cpc-button')][contains(text(),'%s')]";

    public void click() {
        log.info(String.format("Click button '%s'.", label));
        jsAction.clickByJs(driver.findElement(By.xpath(String.format(BUTTON_XPATH, label))));
    }
}