package Utilities;

import io.restassured.path.json.JsonPath;

public class ConvertJsonPath {
	// Converts response to JsonPath and extracts the value of the given key
	public static String convertJsonpath(String response, String key) {
		JsonPath path = new JsonPath(response);
		return path.getString(key); // Extracts the value of the given key
	}
}