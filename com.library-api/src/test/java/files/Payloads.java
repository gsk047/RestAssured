package files;

public class Payloads {

	public static String toAddBook(String isbn, String aisle) {
		String addBookPayload = "{\r\n" + "  \"name\": \"Learn Read Automation with Java\",\r\n" + "  \"isbn\": \""
				+ isbn + "\",\r\n" + "  \"aisle\": \"" + aisle + "\",\r\n" + "  \"author\": \"Suna Pana\"\r\n" + "}";
		return addBookPayload;
	}

	public static String toDeleteBook(String ID) {
		String deleteBookPayload = "{\r\n" + "  \"ID\": \"" + ID + "\"\r\n" + "}";
		return deleteBookPayload;
	}

}
