package org.cloud_google.utils;

import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Log4j
public final class PropertyReader {
    private static InputStream inputStream;

    private static String getCorrectPath(String fileName) {
        return "/env/" + fileName + ".properties";
    }

    public static Properties readProperties(String fileName) {
        Properties properties = new Properties();
        try {
            inputStream = PropertyReader.class.getResourceAsStream(getCorrectPath(fileName));
            if (inputStream != null)
                properties.load(inputStream);
        } catch (Exception ex) {
            log.fatal("ERROR: " + ex.getMessage());
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.fatal("ERROR: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }
}