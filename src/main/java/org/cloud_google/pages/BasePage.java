package org.cloud_google.pages;

import lombok.extern.log4j.Log4j;
import org.cloud_google.utils.JsAction;
import org.cloud_google.utils.Operation;
import org.cloud_google.utils.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Log4j
public class BasePage {

    WebDriver driver;
    Waiter waiter;
    Operation operation;
    JsAction jsAction;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        waiter = new Waiter(driver);
        operation = new Operation(driver);
        jsAction = new JsAction(driver);
        PageFactory.initElements(driver, this);
    }

    public void openPage(String url) {
        log.info(String.format("Open URL: '%s'", url));
        driver.get(url);
    }
}