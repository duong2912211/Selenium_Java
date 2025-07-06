package runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Hooks {
    public static WebDriver driver;
    @Before
    public void browserSetUp(){
        System.out.println(">>> Starting browser session...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
    }

    @After
    public void tearDown() {
        System.out.println(">>> Closing browser session...");
        if (driver != null) {
            driver.quit();
        }
    }

    public static WebDriver getDriver()
    {
        return driver;
    }
}
