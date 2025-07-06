package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",       // ✅ path to .feature files
        glue = {"step_definition","runner"},                     // ✅ package where step definitions are
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true
)
public class Test {}
