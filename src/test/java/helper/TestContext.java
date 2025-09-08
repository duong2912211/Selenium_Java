package helper;

import org.json.JSONObject;

import java.util.HashMap;

import static runner.Hooks.scenarioNumberialOrder;

public class TestContext {
    private static JSONObject allData = new JSONObject();

    public static void addData(HashMap<String, String> data) {
        System.out.println(scenarioNumberialOrder);
        allData.put(scenarioNumberialOrder, new JSONObject(data));
    }

    public static JSONObject getAllData() {
        return allData;
    }

    public static void clearData() {
        allData = new JSONObject();
    }
}
