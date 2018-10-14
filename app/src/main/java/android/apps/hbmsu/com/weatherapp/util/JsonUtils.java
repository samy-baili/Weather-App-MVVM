package android.apps.hbmsu.com.weatherapp.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {

    public static JsonObject stringToJson(String json) {
        return (JsonObject) new JsonParser().parse(json);
    }
}
