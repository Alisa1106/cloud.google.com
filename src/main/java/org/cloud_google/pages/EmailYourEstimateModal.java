package org.cloud_google.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.cloud_google.utils.SwitchingBetweenTabs;
import org.cloud_google.utils.SwitchingToTwoFrames;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.yopmail.pages.YopmailHomePage;

@Log4j
public class EmailYourEstimateModal extends Header {

    public EmailYourEstimateModal(WebDriver driver) {
        super(driver);
    }

    public static final String YOPMAIL_URL = "https://yopmail.com/";

    @FindBy(xpath = "//*[@type='email']")
    WebElement emailInput;

    @FindBy(xpath = "//button[contains(text(),'Send Email')]")
    WebElement sendEmailButton;

    public EmailYourEstimateModal waitForPageOpened() {
        waiter.waitForElementLocated(emailInput, 5);
        return this;
    }

    @Step("Open yopmail URL in new tab")
    public EmailYourEstimateModal openNewTab() {
        log.info(String.format("Open URL: '%s' in new tab", YOPMAIL_URL));
        jsAction.openNewTabByJs(YOPMAIL_URL);
        return this;
    }

    @Step("Switch to opened tab")
    public YopmailHomePage goToOtherTab() {
        SwitchingBetweenTabs.switchToOtherTab(driver, 1);
        return new YopmailHomePage(driver);
    }

    @Step("Write email: {email} in email input field")
    public EmailYourEstimateModal writeEmailInEmailInputField(String email) {
        waiter.waitForElementLocated(emailInput, 5);
        log.info(String.format("Write email '%s' in email input field", email));
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Click send email button")
    public GoogleCloudPricingCalculatorPage clickSendEmailButton() {
        waiter.waitForElementClickable(sendEmailButton, 5);
        log.info("Click send email button");
        jsAction.clickByJs(sendEmailButton);
        return new GoogleCloudPricingCalculatorPage(driver);
    }

    @Step("Switch to two frames")
    public EmailYourEstimateModal switchToTwoFrames() {
        SwitchingToTwoFrames.switchToFrames(driver);
        return this;
    }
}