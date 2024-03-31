package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationHelper {
    private static final String CONFIG_FILE = "resources/config.properties";
    private static final String ACCOUNT_INFO = "resources/account.properties";

    public static String getBrowser() {
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
            return properties.getProperty("browser");
        } catch (IOException e) {
            e.printStackTrace();
            return "chrome"; // default to Chrome if config file or property not found
        }
    }

    public static String getAccountInfo(String key) {
        Properties properties = new Properties();

        try (FileInputStream fis = new FileInputStream(ACCOUNT_INFO)) {
            properties.load(fis);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return "Error reading file";
        }
    }
}