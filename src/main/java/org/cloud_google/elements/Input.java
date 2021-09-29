package org.cloud_google.elements;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j
public class Input {

    WebDriver driver;
    String label;

    public Input(WebDriver driver, String label) {
        this.driver = driver;
        this.label = label;
    }

    public static final String INPUT_XPATH = "//*[contains(@id,'input_')]|*[contains(text(),'%s')]";

    public void writeText(String text) {
        log.info(String.format("Write text: '%s' in input field: '%s'", text, label));
        driver.findElement(By.xpath(String.format(INPUT_XPATH, label))).sendKeys(text);
    }
}