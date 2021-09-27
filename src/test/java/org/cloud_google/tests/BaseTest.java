package org.cloud_google.tests;

import lombok.extern.log4j.Log4j;
import org.cloud_google.drivers.DriverCreator;
import org.cloud_google.steps.Steps;
import org.cloud_google.utils.Properties;
import org.cloud_google.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Log4j
@Listeners(TestListener.class)
public class BaseTest {

    static Properties properties = new Properties();
    WebDriver driver;
    Steps steps;

    @BeforeMethod
    public void initTest(ITestContext context) {
        driver = DriverCreator.create(properties.getBrowser()).createDriver();
        driver.manage().window().maximize();
        initSteps();
        String variable = "driver";
        log.debug("Setting driver into context with variable name " + variable);
        context.setAttribute(variable, driver);
    }

    @AfterMethod(alwaysRun = true)
    public void endTest() {
        log.debug("Close browser");
        driver.quit();
    }

    public void initSteps() {
        steps = new Steps(driver);
    }
}