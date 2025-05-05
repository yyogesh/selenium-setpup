package framework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
/**
 * Manages configuration properties for the test framework
 */
public class ConfigManager {
    private static final Properties properties = new Properties();
    private static ConfigManager instance;

    private ConfigManager() {
        try {
            // Load properties from config file
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            System.err.println("Error loading config.properties: " + e.getMessage());
            throw new RuntimeException("Failed to load config properties", e);
        }
    }

    public static synchronized ConfigManager getInstance() {
        if (instance == null) {
            instance = new ConfigManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getBaseUrl() {
        return getProperty("base.url");
    }

    public String getBrowser() {
        return getProperty("browser");
    }

    public long getImplicitWaitTime() {
        return Long.parseLong(getProperty("implicit.wait"));
    }

    public String getUsername() {
        return getProperty("username");
    }

    public String getPassword() {
        return getProperty("password");
    }
}
