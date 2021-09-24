package org.cloud_google.utils;

import lombok.Data;

@Data
public class Properties {

    private String browser;

    public Properties() {
        java.util.Properties commonProps = PropertyReader.readProperties("common");
        browser = commonProps.getProperty("browser");
    }
}