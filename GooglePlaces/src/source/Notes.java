package source;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
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
				.post("maps/api/place/add/json").then().assertThat().statusCode(200)
				.body("scope", containsString("APP")).body("status", equalTo("OK"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asPrettyString();

		JsonPath jsonPathAdd = new JsonPath(addPlaceResponse);
		String placeID = jsonPathAdd.getString("place_id");

		System.out.println("Add Place Response: ");
		System.out.println(addPlaceResponse);
		System.out.println("Place ID value is: " + placeID);

		// Update Place with new Address
		String updatePlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
				.header("Content-Type", "application/json").body(Payload.UpdatePlace(placeID)).when()
				.put("maps/api/place/update/json").then().assertThat().statusCode(200).extract().response()
				.asPrettyString();

		System.out.println("Update Place Response");
		System.out.println(updatePlaceResponse);

		JsonPath jsonPathUpdate = new JsonPath(Payload.UpdatePlace(placeID));
		String updatedAddress = jsonPathUpdate.getString("address");
		System.out.println("Updated Address:" + updatedAddress);

		// Get the Place details
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeID)
				.when().get("maps/api/place/get/json").then().assertThat().statusCode(200)
				.body("address", equalTo(updatedAddress)).extract().response().asPrettyString();

		System.out.println("Get Place Response:");
		System.out.println(getPlaceResponse);
	}

}
