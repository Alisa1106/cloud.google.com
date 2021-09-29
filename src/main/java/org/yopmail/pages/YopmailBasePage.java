package org.yopmail.pages;

import lombok.extern.log4j.Log4j;
import org.cloud_google.utils.Waiter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Log4j
public class YopmailBasePage {

    WebDriver driver;
    Waiter waiter;

    public YopmailBasePage(WebDriver driver) {
        this.driver = driver;
        waiter = new Waiter(driver);
        PageFactory.initElements(driver, this);
    }
}