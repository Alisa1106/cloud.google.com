package org.cloud_google.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Log4j
public class FirefoxDriverCreator implements WebDriverCreator {

    @Override
    public WebDriver createDriver() {
        WebDriverManager.firefoxdriver().setup();
        try {
            return new FirefoxDriver();
        } catch (SessionNotCreatedException e) {
            log.fatal("ERROR: FirefoxDriver is not started. " + e.getMessage());
        }
        return null;
    }
}