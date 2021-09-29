package org.cloud_google.utils;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

@Log4j
public class SwitchingBetweenTabs {

    public static void switchToOtherTab(WebDriver driver, int numberOfTab) {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        log.info("Switch to other tab");
        driver.switchTo().window(tabs.get(numberOfTab));
    }
}