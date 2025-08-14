package runner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

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
    public static void initializeDriver() {
        if (driverThreadLocal.get() == null) {
            // Setup ChromeDriver automatically
            WebDriverManager.chromedriver().setup();

            ChromeDriver driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

            // Store in ThreadLocal for thread safety
            driverThreadLocal.set(driver);
        }
    }

    /**
     * Get WebDriver instance for current thread
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver == null) {
            initializeDriver();
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
     * @return true if WebDriver exists
     */
    public static boolean isDriverInitialized() {
        return driverThreadLocal.get() != null;
    }
}