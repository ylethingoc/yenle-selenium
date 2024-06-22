package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class JSONUtils {

    public static Map<String, Map<String, String>> getTestData(String key) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Map<String, Map<String, String>>> data = mapper.readValue(
                    new File("resources/testdata.json"),
                    new TypeReference<Map<String, Map<String, Map<String, String>>>>() {}
            );
            return data.get(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}