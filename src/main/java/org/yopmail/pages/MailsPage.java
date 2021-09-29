package org.yopmail.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.cloud_google.pages.EmailYourEstimateModal;
import org.cloud_google.utils.SwitchingBetweenTabs;
import org.cloud_google.utils.SwitchingToDefaultContent;
import org.cloud_google.utils.SwitchingToFrame;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j
public class MailsPage extends YopmailBasePage {

    public MailsPage(WebDriver driver) {
        super(driver);
    }

    public static final String MAIL_TITLE_XPATH = "//*[@class='lms'][text()='%s']";
    public static final String TOTAL_ESTIMATE_COST_PARAMETERS_XPATH = "//h3";
    public static final String MAIL_BUTTONS_XPATH = "//button[contains(@class,'but')]//*[text()='%s']";

    @FindBy(className = "bname")
    WebElement emailNameContainer;

    @FindBy(id = "refresh")
    WebElement refreshMailsButton;

    @Step("Get email")
    public String getEmail() {
        return emailNameContainer.getText();
    }

    @Step("Switch to first tab")
    public EmailYourEstimateModal switchToOtherTab() {
        SwitchingBetweenTabs.switchToOtherTab(driver, 0);
        return new EmailYourEstimateModal(driver);
    }

    @Step("Click refresh mails button")
    public MailsPage refreshMails() {
        log.info("Click refresh mails button");
        refreshMailsButton.click();
        return this;
    }

    @Step("Switch to frame: {frameId}")
    public MailsPage switchToFrame(String frameId) {
        log.info(String.format("Switch to frame: '%s'", frameId));
        SwitchingToFrame.switchByFrameId(driver, frameId);
        return this;
    }

    @Step("Switch to default content")
    public MailsPage switchToDefaultContent() {
        log.info("Switch to default content");
        SwitchingToDefaultContent.switchToDefault(driver);
        return this;
    }

    @Step("Open email: {emailTitleName}")
    public MailsPage openEmail(String emailTitleName) {
        switchToFrame("ifinbox");
        String emailTitle = String.format(MAIL_TITLE_XPATH, emailTitleName);
        while (driver.findElements(By.xpath(emailTitle)).isEmpty()) {
            switchToDefaultContent();
            refreshMails();
            switchToFrame("ifinbox");
        }
        log.info(String.format("Click email title: '%s'", emailTitleName));
        driver.findElement(By.xpath(emailTitle)).click();
        switchToDefaultContent();
        return this;
    }

    @Step("Get total estimate cost")
    public String getTotalEstimateCost() {
        switchToFrame("ifmail");
        return driver.findElements(By.xpath(TOTAL_ESTIMATE_COST_PARAMETERS_XPATH)).get(1).getText();
    }

    @Step("Click button {buttonName}")
    public MailsPage deleteMessage(String buttonName) {
        log.info(String.format("Click button: '%s'", buttonName));
        driver.findElement(By.xpath(String.format(MAIL_BUTTONS_XPATH, buttonName))).click();
        return this;
    }
}