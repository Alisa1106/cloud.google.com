package org.cloud_google.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class HomePage extends Header {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public static final String CLOUD_GOOGLE_HOMEPAGE_URL = "https://cloud.google.com/";

    @Step("Open home page")
    public HomePage openPage() {
        super.openPage(CLOUD_GOOGLE_HOMEPAGE_URL);
        return this;
    }

    public HomePage waitForPageOpened() {
        waiter.waitForPageLoaded();
        return this;
    }

    public HomePage clickHeaderSearchButton() {
        super.clickSearchButton();
        return this;
    }
}