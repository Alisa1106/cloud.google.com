package org.yopmail.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j
public class YopmailHomePage extends YopmailBasePage {

    public YopmailHomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "login")
    WebElement emailNameField;

    @FindBy(xpath = "//button[@class='md']")
    WebElement goToEmailButton;

    @Step("Write email name: {emailName} in email input field")
    public YopmailHomePage writeEmailName(String emailName) {
        log.info(String.format("Write email name: '%s' in email input field", emailName));
        emailNameField.sendKeys(emailName);
        return this;
    }

    @Step("Click go to mails button")
    public MailsPage clickGoToMailsButton() {
        log.info("Click go to mails button");
        goToEmailButton.click();
        return new MailsPage(driver);
    }
}