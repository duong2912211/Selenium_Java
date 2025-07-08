package helper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Scenario;

import java.io.File;
import java.io.IOException;

public class JsonDataReader {
    private static JsonNode rootNode;

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            rootNode = mapper.readTree(new File("src/test/resources/data/functionalData.json"));
        } catch (IOException e) {
            throw new RuntimeException("❌ Failed to load testData.json", e);
        }
    }

    public static String get(String scenarioKey, String fieldName) {
        return rootNode.path(scenarioKey).path(fieldName).asText();
    }
}
