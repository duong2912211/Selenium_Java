package runner;

import helper.TestContext;
import io.cucumber.java.*;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.nio.file.Files;
import java.nio.file.Path;

import static runner.DriverManager.getDriver;

public class Hooks {

    public static String scenarioNumberialOrder;

    @AfterAll
    public static void afterAll() {
        try {
            // Get the collected data
            JSONObject data = TestContext.getAllData();

            // Write it to file under target/testdata.json
            Path file = Path.of("target", "testdata.json");
            Files.createDirectories(file.getParent()); // ensure folder exists
            Files.writeString(file, data.toString(4));

            System.out.println("✅ JSON test data written to: " + file.toAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getScenarioPrefix() {
        return scenarioNumberialOrder;
    }

    @Before(order = 1)
    public void browserSetup() {
        System.out.println(">>> Starting browser session...");
        String browser = System.getProperty("browser", "chrome");
        // Initialize WebDriver before each scenario
        DriverManager.initializeDriver(browser);
        if (DriverManager.isDriverInitialized()) {
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
}