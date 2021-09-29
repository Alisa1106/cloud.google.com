package org.cloud_google.utils;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;

@Log4j
public class SwitchingToTwoFrames {

    public static void switchToFrames(WebDriver driver) {
        log.info("Switch to first frame");
        SwitchingToFrame.switchByFrameNumber(driver, 0);
        log.info("Switch to second frame");
        SwitchingToFrame.switchByFrameId(driver, "myFrame");
    }
}