package runner;

import helper.ScenarioContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import runner.DriverManager;

import static runner.DriverManager.getDriver;

public class Hooks {

    public static String scenarioNumberialOrder;
    public static ScenarioContext scenarioContext;

    @Before(order = 2)
    public void beforeScenario() {
        scenarioContext = new ScenarioContext(); // fresh context for each scenario
    }

    @Before(order = 1)
    public void browserSetup() {
        System.out.println(">>> Starting browser session...");
        String browser = System.getProperty("browser", "chrome");
        // Initialize WebDriver before each scenario
        DriverManager.initializeDriver(browser);
        if (DriverManager.isDriverInitialized()){
            System.out.println("WebDriver initialized successfully");
        }
    }

    @Before(order = 0)
    public void beforeScenario(Scenario scenario) {
        scenarioNumberialOrder = scenario.getName().substring(0, 5); // example: "01.02"
        System.out.println("Scenario: " + scenarioNumberialOrder);
    }

    @After
    public void tearDown(Scenario scenario) {
        // Take screenshot if scenario fails (optional)
        if (scenario.isFailed()) {
            System.out.println("Scenario failed: " + scenario.getName());
            // Add screenshot logic here if needed
        }

        if (scenarioContext != null) {
            scenarioContext.clear(); // clean up after scenario
        }

        // Clean up WebDriver after each scenario
        DriverManager.quitDriver();
        System.out.println("Browser session closed");
    }

    @AfterStep
    public void takeScreenshotAfterStep(Scenario scenario) {
        try {
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Step Screenshot");
        } catch (Exception e) {
            System.out.println("Screenshot capture failed: " + e.getMessage());
        }
    }

    public static String getScenarioPrefix() {
        return scenarioNumberialOrder;
    }
}