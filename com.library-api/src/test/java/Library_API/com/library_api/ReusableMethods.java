package Library_API.com.library_api;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath addJsonPath(String payload) {
		JsonPath jsonPath = new JsonPath(payload);
		return jsonPath;
	}
	
}
