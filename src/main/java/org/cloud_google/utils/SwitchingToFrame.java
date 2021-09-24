package org.cloud_google.utils;

import org.openqa.selenium.WebDriver;

public class SwitchingToFrame {


    public static void switchByFrameNumber(WebDriver driver, int number) {
        driver.switchTo().frame(number);
    }

    public static void switchByFrameId(WebDriver driver, String frameId) {
        driver.switchTo().frame(frameId);
    }
}