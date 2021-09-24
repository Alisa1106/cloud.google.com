package org.cloud_google.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsAction {

    JavascriptExecutor executor;

    public JsAction(WebDriver driver) {
        executor = (JavascriptExecutor) driver;
    }

    public void clickByJs(WebElement element) {
        executor.executeScript("arguments[0].click();", element);
    }

    public void openNewTabByJs(String url) {
        executor.executeScript("window.open('" + url + "','_blank');");
    }
}