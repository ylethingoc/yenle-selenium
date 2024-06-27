package com.saferailway.utils;

public class GlobalVariables {
    private ConfigParser configParser;

    public GlobalVariables(ConfigParser configParser) {
        this.configParser = configParser;
    }

    public String getBrowser() {
        return configParser.getProperty("browser");
    }

    public String getHeadlessConfig() {
        return configParser.getProperty("headless");
    }

    public String getBaseUrl() {
        return configParser.getProperty("baseUrl");
    }

    public static int WAIT_TIME_60_SECS = 60;
    public static int WAIT_TIME_30_SECS = 30;
}