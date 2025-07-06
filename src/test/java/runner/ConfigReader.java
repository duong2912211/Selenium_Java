package runner;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties = new Properties();
    private static String env;

    public static void load(String environment) {
        env = environment;
        try (InputStream input = new FileInputStream("src/test/resources/config/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(env + "." + key);
    }
}
