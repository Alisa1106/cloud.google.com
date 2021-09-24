package org.cloud_google.pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j;
import org.cloud_google.models.ComputeEngine;
import org.cloud_google.utils.SwitchingBetweenTabs;
import org.cloud_google.utils.SwitchingToTwoFrames;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.yopmail.pages.MailsPage;

@Log4j
public class GoogleCloudPricingCalculatorPage extends Header {

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public static final String TOP_BUTTONS_XPATH = "//*[contains(@class,'md-ink-ripple')]//*[@class='name ng-binding'][contains(text(),'%s')]";

    @FindBy(xpath = "//h2//*[@class='ng-binding']")
    WebElement totalEstimatedCost;

    public GoogleCloudPricingCalculatorPage waitForPageOpened() {
        waiter.waitForPageLoaded();
        return this;
    }

    @Step("Switch to two frames")
    public GoogleCloudPricingCalculatorPage switchToTwoFrames() {
        SwitchingToTwoFrames.switchToFrames(driver);
        return this;
    }

    @Step("Click button {buttonName}")
    public GoogleCloudPricingCalculatorPage clickGoToFormButton(String buttonName) {
        String topButtonName = String.format(TOP_BUTTONS_XPATH, buttonName);
        waiter.waitForElementLocated(By.xpath(topButtonName), 5);
        log.info(String.format("Click button: '%s'", buttonName));
        driver.findElement(By.xpath(topButtonName)).click();
        return this;
    }

    @Step("Create compute engine")
    public GoogleCloudPricingCalculatorPage fillForm(ComputeEngine computeEngine) {
        operation.fillInputField("Number of instances", computeEngine.getNumberOfInstances());
        operation.selectDropdownOption("Operating System / Software", computeEngine.getOperatingSystem());
        operation.selectDropdownOption("Machine Class", computeEngine.getVirtualMachineClass());
        operation.selectDropdownOption("Series", computeEngine.getSeries());
        operation.selectDropdownOption("Machine type", computeEngine.getInstanceType());
        operation.markCheckbox("Add GPUs.");
        operation.selectDropdownOption("Number of GPUs", computeEngine.getNumberOfGpu());
        operation.selectDropdownOption("GPU type", computeEngine.getGpuType());
        operation.selectDropdownOption("Local SSD", computeEngine.getLocalSsd());
        operation.selectDropdownOption("Datacenter location", computeEngine.getDatacenterLocation());
        operation.selectDropdownOption("Committed usage", computeEngine.getCommittedUsage());
        return this;
    }

    @Step("Click add to estimate button")
    public GoogleCloudPricingCalculatorPage clickAddToEstimateButton() {
        operation.clickButton("Add to Estimate");
        return this;
    }

    public String getTotalEstimatedCostString() {
        return totalEstimatedCost.getText();
    }

    public String getTotalEstimateCost() {
        return getTotalEstimatedCostString().substring(22, 34);
    }

    @Step("Click button: 'Email Estimate'")
    public EmailYourEstimateModal clickEmailEstimateButton() {
        operation.clickButton("Email Estimate");
        return new EmailYourEstimateModal(driver);
    }

    @Step("Switch to opened tab")
    public MailsPage goToOtherTab() {
        SwitchingBetweenTabs.switchToOtherTab(driver, 1);
        return new MailsPage(driver);
    }
}