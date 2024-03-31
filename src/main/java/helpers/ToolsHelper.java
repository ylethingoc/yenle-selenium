package helpers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ToolsHelper {

    public static void writeKeyValue(String filename, String key, String value) {
        Properties properties = new Properties();

        try {
            File file = new File(System.getProperty("user.dir") + "/resources/" + filename);
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    properties.load(fis);
                }
            }

            properties.setProperty(key, value);

            try (FileOutputStream fos = new FileOutputStream(file)) {
                properties.store(fos, "Account to login into Safe Railway");
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

}
