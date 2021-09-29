package org.cloud_google.utils;

import com.epam.reportportal.message.ReportPortalMessage;
import com.epam.reportportal.testng.BaseTestNGListener;
import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

@Log4j
public class MyListener extends BaseTestNGListener {

    public MyListener() {
        super(new ParamOverrideTestNgService());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info(String.format("======================================== STARTING TEST '%s' ========================================%n", iTestResult.getName()));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        log.info(String.format("======================================== FINISHED TEST '%s' Duration: %ss ========================================%n", iTestResult.getName(),
                getExecutionTime(iTestResult)));
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info(String.format("======================================== FAILED TEST '%s' Duration: %ss ========================================%n", iTestResult.getName(),
                getExecutionTime(iTestResult)));
        log.info(getReportPortalMessage(iTestResult));
        takeScreenshot(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.info(String.format("======================================== SKIPPING TEST '%s' ========================================%n", iTestResult.getName()));
        takeScreenshot(iTestResult);
    }

    @Attachment(value = "Last screen state", type = "image/png")
    private byte[] takeScreenshot(ITestResult iTestResult) {
        ITestContext context = iTestResult.getTestContext();
        try {
            WebDriver driver = (WebDriver) context.getAttribute("driver");
            if (driver != null) {
                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screenshot, new File("target/screenshots/" + getCurrentTimeAsString() + ".png"));
                return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            } else {
                return new byte[]{};
            }
        } catch (NoSuchSessionException | IllegalStateException | IOException ex) {
            return new byte[]{};
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }

    public ReportPortalMessage getReportPortalMessage(ITestResult iTestResult) {
        ITestContext context = iTestResult.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("driver");
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("target/screenshots/" + getCurrentTimeAsString() + ".png"));
            String reportPortalMessage = "ERROR screenshot";
            return new ReportPortalMessage(screenshot, reportPortalMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}