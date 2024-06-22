package utils;

public class GlobalVariables {
    private ConfigParser configParser;

    public GlobalVariables(ConfigParser configParser) {
        this.configParser = configParser;
    }

    public String getEmail() {
        return configParser.getProperty("email");
    }

    public String getPassword() {
        return configParser.getProperty("password");
    }

    public String getBrowser() {
        return configParser.getProperty("browser");
    }

    public String getBaseUrl() {
        return configParser.getProperty("baseUrl");
    }

    public static int WAIT_TIME_30_SECS = 30;
}