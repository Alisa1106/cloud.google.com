package org.cloud_google.steps;

import io.qameta.allure.Step;
import org.cloud_google.models.ComputeEngine;
import org.cloud_google.pages.EmailYourEstimateModal;
import org.cloud_google.pages.GoogleCloudPricingCalculatorPage;
import org.cloud_google.pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.yopmail.pages.MailsPage;

public class Steps {

    private final HomePage homePage;
    private final GoogleCloudPricingCalculatorPage googleCloudPricingCalculatorPage;
    private final EmailYourEstimateModal emailYourEstimateModal;
    private final MailsPage mailsPage;

    public Steps(WebDriver driver) {
        homePage = new HomePage(driver);
        googleCloudPricingCalculatorPage = new GoogleCloudPricingCalculatorPage(driver);
        emailYourEstimateModal = new EmailYourEstimateModal(driver);
        mailsPage = new MailsPage(driver);
    }

    @Step("Open home page, search Pricing Calculator, fill parameter form and go to estimate")
    public Steps selectParameterToEstimate(String searchingRequest, String linkText, String buttonName, ComputeEngine computeEngine) {
        homePage.openPage()
                .waitForPageOpened()
                .clickHeaderSearchButton()
                .fillInSearchField(searchingRequest)
                .goToSearchResults()
                .goToPrisingCalculatorPage(linkText)
                .waitForPageOpened()
                .switchToTwoFrames()
                .clickGoToFormButton(buttonName)
                .fillForm(computeEngine)
                .clickAddToEstimateButton();
        return this;
    }

    @Step("Get total estimate cost")
    public String getTotalEstimatedCost() {
        return googleCloudPricingCalculatorPage.getTotalEstimateCost();
    }

    @Step("Go to send email form")
    public Steps goToSendEmailForm() {
        googleCloudPricingCalculatorPage.clickEmailEstimateButton();
        return this;
    }

    @Step("Open email service in new tab and get email address")
    public String getEmailAddress(String emailName) {
        return emailYourEstimateModal.waitForPageOpened()
                .openNewTab()
                .goToOtherTab()
                .writeEmailName(emailName)
                .clickGoToMailsButton()
                .getEmail();
    }

    @Step("Return to Cloud google tab, fill in email field, send email, then go to email page and get total price in email")
    public String sendAndCheckEmail(String email, String emailTitleName)  {
        return mailsPage.switchToOtherTab()
                .switchToTwoFrames()
                .writeEmailInEmailInputField(email)
                .clickSendEmailButton()
                .goToOtherTab()
                .refreshMails()
                .openEmail(emailTitleName)
                .getTotalEstimateCost();
    }

    public Steps deleteMessage(String buttonName) {
        mailsPage.deleteMessage(buttonName);
        return this;
    }
}