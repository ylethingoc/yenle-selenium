package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JsonParser {
    private JsonNode rootNode;

    public JsonParser(String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            rootNode = objectMapper.readTree(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getTestDataForTestClass(String testClassName) {
        Map<String, String> testData = new HashMap<>();

        JsonNode testNode = rootNode.path(testClassName);
        if (testNode.isMissingNode()) {
            System.out.println("Test case not found: " + testClassName);
            return testData;
        }

        Iterator<Map.Entry<String, JsonNode>> fields = testNode.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> field = fields.next();
            testData.put(field.getKey(), field.getValue().asText());
        }

        return testData;
    }
}