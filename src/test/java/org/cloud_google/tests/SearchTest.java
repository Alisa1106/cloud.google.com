package org.cloud_google.tests;

import org.cloud_google.helpers.ComputeEngineCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    public static final String SEARCH_REQUEST_TEXT = "Google Cloud Platform Pricing Calculator";
    public static final String COMPUTE_ENGINE_BUTTON_NAME = "Compute Engine";
    public static final String EMAIL_NAME = "cloudgoogle";
    public static final String GOOGLE_CLOUD_PRISE_ESTIMATE_EMAIL_TITLE_NAME = "Google Cloud Platform Price Estimate";
    public static final String DELETE_BUTTON_NAME = "Delete";

    @Test(description = "Check that Total Estimated Monthly Cost in mail matches with Total Estimated Monthly Cost in Estimate")
    public void searchTest() throws InterruptedException {
        ComputeEngineCreator creator = new ComputeEngineCreator();
        steps.selectParameterToEstimate(SEARCH_REQUEST_TEXT, SEARCH_REQUEST_TEXT, COMPUTE_ENGINE_BUTTON_NAME, creator.create());
        String totalEstimatedCost = steps.getTotalEstimatedCost();
        steps.goToSendEmailForm();
        String email = steps.getEmailAddress(EMAIL_NAME);
        Assert.assertEquals(steps.sendAndCheckEmail(email, GOOGLE_CLOUD_PRISE_ESTIMATE_EMAIL_TITLE_NAME), totalEstimatedCost);
        steps.deleteMessage(DELETE_BUTTON_NAME);
    }
}