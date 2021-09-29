package org.cloud_google.utils;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Log4j
public class Waiter {

    WebDriver driver;
    WebDriverWait wait;

    public Waiter(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementLocated(WebElement element, int timeout) {
        try {
            wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            log.error("Element: " + element + " isn't found after " + timeout + e.getLocalizedMessage());
        }
    }

    public void waitForElementClickable(WebElement element, int timeout) {
        try {
            wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            log.error("Element: " + element + " isn't found after " + timeout + e.getLocalizedMessage());
        }
    }

    public void waitForElementLocated(By element, int timeout) {
        try {
            wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException e) {
            log.error("Element: " + element + " isn't found after " + timeout + e.getLocalizedMessage());
        }
    }

    public void waitForPageLoaded() {
        new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
    }
}