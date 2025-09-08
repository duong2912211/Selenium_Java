package runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * WebDriver Manager using Singleton pattern
 * Thread-safe implementation for parallel execution
 */
public class DriverManager {

    // ThreadLocal ensures each thread gets its own WebDriver instance
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Initialize WebDriver for current thread
     */
    public static void initializeDriver(String browserName) {

        // Create prefs map
        Map<String, Object> prefs = new HashMap<>();
        // 1 = Allow, 2 = Block
        prefs.put("profile.default_content_setting_values.notifications", 2);

        if (driverThreadLocal.get() == null) {
            WebDriver driver;

            switch (browserName.toLowerCase()) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;

                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();

                    ChromeOptions options = new ChromeOptions();
                    options.setExperimentalOption("prefs", prefs);
                    // Always unique profile dir to avoid lock in CI
//                    String tempProfile = System.getProperty("java.io.tmpdir") + "/chrome-profile-" + System.nanoTime();
//                    options.addArguments("--user-data-dir=" + tempProfile);

                    // Detect CI environment (Azure Pipelines, GitHub Actions, etc.)
                    if (System.getenv("TF_BUILD") != null || System.getenv("CI") != null) {
                        options.addArguments("--headless=new");
                        options.addArguments("--no-sandbox");
                        options.addArguments("--disable-dev-shm-usage");
                        options.addArguments("--disable-gpu");
                        options.addArguments("--remote-allow-origins=*");
                        options.addArguments("--user-data-dir=" + System.getProperty("java.io.tmpdir") + "/chrome-" + System.currentTimeMillis());
                    }

                    driver = new ChromeDriver(options);
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

            // Store in ThreadLocal for thread safety
            driverThreadLocal.set(driver);
        }
    }

    /**
     * Get WebDriver instance for current thread
     *
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome");
            initializeDriver(browser);
            driver = driverThreadLocal.get();
        }
        return driver;
    }

    /**
     * Quit WebDriver and clean up ThreadLocal
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }

    /**
     * Check if WebDriver is initialized
     *
     * @return true if WebDriver exists
     */
    public static boolean isDriverInitialized() {
        return driverThreadLocal.get() != null;
    }
}