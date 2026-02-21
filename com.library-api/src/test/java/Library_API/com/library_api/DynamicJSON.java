package Library_API.com.library_api;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.UUID;

public class DynamicJSON {

	@Test
	public void addBook() {

		RestAssured.baseURI = "http://216.10.245.166";

		// To Generate Random Values
		String isbn = UUID.randomUUID().toString().substring(0, 4);
		String aisle = UUID.randomUUID().toString().substring(0, 4);

		// Add Book
		String addBookResponse = given().log().all().header("Content-Type", "application/json")
				.body(Payloads.toAddBook(isbn, aisle)).when().post("Library/Addbook.php").then().assertThat()
				.statusCode(200).extract().response().asPrettyString();
		System.out.println(addBookResponse);
		JsonPath addBookPath = ReusableMethods.addJsonPath(addBookResponse);
		String bookID = addBookPath.get("ID");
		System.out.println("Book ID: " + bookID);

		// Delete Book
		String deleteBookResponse = given().log().all().header("Content-Type", "application/json")
				.body(Payloads.toDeleteBook(bookID)).when().post("Library/DeleteBook.php").then().assertThat()
				.statusCode(200).extract().response().asPrettyString();
		JsonPath deleteBookPath = ReusableMethods.addJsonPath(deleteBookResponse);
		String deleteMessage = deleteBookPath.get("msg");
		Assert.assertEquals("book is successfully deleted", deleteMessage);

	}

}
