package org.cloud_google.drivers;

public class DriverCreator {

    public static WebDriverCreator create(String browser) {
        switch (browser) {
            case "firefox":
                return new FirefoxDriverCreator();
            default:
                return new ChromeDriverCreator();
        }
    }
}