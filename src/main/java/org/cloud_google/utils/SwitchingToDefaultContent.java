package org.cloud_google.utils;

import org.openqa.selenium.WebDriver;

public class SwitchingToDefaultContent {

    public static void switchToDefault(WebDriver driver) {
        driver.switchTo().defaultContent();
    }
}