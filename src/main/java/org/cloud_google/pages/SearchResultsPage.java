package org.cloud_google.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j
public class SearchResultsPage extends Header {

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public static final String SEARCH_RESULT_XPATH = "//*[contains(@class,'gsc-')]//*[contains(text(),'%s')]";

    @Step("Click link: {linkText}")
    public GoogleCloudPricingCalculatorPage goToPrisingCalculatorPage(String linkText) {
        String searchResult = String.format(SEARCH_RESULT_XPATH, linkText);
        waiter.waitForElementLocated(By.xpath(searchResult), 10);
        log.info(String.format("Click link: '%s'", linkText));
        driver.findElement(By.xpath(searchResult)).click();
        return new GoogleCloudPricingCalculatorPage(driver);
    }
}