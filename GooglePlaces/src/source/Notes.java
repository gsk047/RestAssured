package source;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Payload;

//import io.restassured.RestAssured;

public class Notes {

//	public static String placeID;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		 * Validate if Add Place API us working as expected
		 * 
		 * given => All input details when => Submit the API -> resource and http method
		 * then => validate the response
		 * 
		 */

		// Add Place -> Update Place with new Address -> Get the Place details to
		// Validate if new Address is present or not

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		// Add Place
		String addPlaceResponse = given().log().all().queryParam("key", "qaclick123")
				.header("Content-Type", "application/json").body(Payload.AddPlace()).when()
				.post("maps/api/place/add/json").then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asPrettyString();

		System.out.println("Response Body: " + addPlaceResponse);

		JsonPath jsonPath1 = ReusableMethods.jsonPath(addPlaceResponse); // To parse the JSON to String

		String place_id = jsonPath1.getString("place_id");
		System.out.println("Place ID: " + place_id);

		// Update Place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Payload.UpdatePlace(place_id)).when().put("maps/api/place/update/json").then().assertThat()
				.statusCode(200).body("msg", equalTo("Address successfully updated"));

		// Get Place
		String updatedAddress = Payload.updatedAddress;
		
		String getPlaceResponse = given().log().all().queryParam("place_id", place_id).queryParam("key", "qaclick123")
				.when().get("maps/api/place/get/json").then().assertThat().statusCode(200).body("address", equalTo(updatedAddress))
				.extract().body().asPrettyString();

		System.out.println("Get Place:" + getPlaceResponse);
		
		JsonPath jsonPath2 = ReusableMethods.jsonPath(getPlaceResponse);
		String actualAddress = jsonPath2.getString("address");
		
		System.out.println("Actual Address: " + actualAddress);
		
		Assert.assertEquals(actualAddress, updatedAddress);
	}

}
