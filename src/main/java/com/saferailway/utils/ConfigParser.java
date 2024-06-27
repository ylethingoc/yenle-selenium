package com.saferailway.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigParser {
    private Properties properties = new Properties();

    public ConfigParser(String filePath) {
        try {
            FileInputStream inputStream = new FileInputStream(filePath);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}