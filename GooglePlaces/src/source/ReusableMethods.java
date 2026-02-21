package source;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath jsonPath(String PathResponse) {
		
		JsonPath jPath = new JsonPath(PathResponse);
		
		return jPath;
	}
	
}
