package org.cloud_google.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j
public class Header extends BasePage {

    public Header(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "devsite-search-container")
    WebElement searchButton;

    @FindBy(className = "devsite-search-field")
    WebElement searchField;

    @Step("Click search button")
    public void clickSearchButton() {
        log.info("Click search button");
        searchButton.click();
    }

    @Step("Write text: {searchRequest} in input field")
    public PopularSearchResultsModal fillInSearchField(String searchRequest) {
        waiter.waitForElementLocated(searchField, 5);
        log.info(String.format("Write text: '%s' in search field", searchRequest));
        searchField.sendKeys(searchRequest);
        return new PopularSearchResultsModal(driver);
    }

    @Step("Click enter button")
    public SearchResultsPage goToSearchResults() {
        log.info("Click enter button");
        searchField.sendKeys(Keys.ENTER);
        return new SearchResultsPage(driver);
    }
}