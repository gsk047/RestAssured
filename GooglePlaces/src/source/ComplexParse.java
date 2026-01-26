package source;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexParse {
	
	public int courseCount;
	public int puchaseAmount;
	public JsonPath jsonPath = new JsonPath(Payload.ComplexParseRequest());

	public void main(String[] args) {

		/*
		 * 1. Print No of courses returned by API
		 * 
		 * 2. Print Purchase Amount
		 * 
		 * 3. Print Title of the first course
		 * 
		 * 4. Print All course titles and their respective Prices
		 * 
		 * 5. Print no of copies sold by RPA Course
		 * 
		 * 6. Verify if Sum of all Course prices matches with Purchase Amount
		 * 
		 */

		

		// 1. Print No of courses returned by API
		courseCount = jsonPath.getInt("courses.size()");
		System.out.println(courseCount);

		// 2. Print Purchase Amount
		puchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
		System.out.println(puchaseAmount);

		// 3. Print Title of the first course
		String title_Course1 = jsonPath.get("courses[0].title");
		System.out.println(title_Course1);

		// 4. Print All course titles and their respective Prices
		for (int i = 0; i < courseCount; i++) {
			String courseTitle = jsonPath.get("courses[" + i + "].title");
			System.out.println(courseTitle);
			int respectivePrices = jsonPath.getInt("courses[" + i + "].price");
			System.out.println(respectivePrices);
		}

		// 5. Print no of copies if sold by RPA Course
		for (int i = 0; i < courseCount; i++) {
			String actualTitle = jsonPath.get("courses[" + i + "].title");
			if (actualTitle.equalsIgnoreCase("RPA")) {
				int rpaTotalCopies = jsonPath.getInt("courses[2].copies");
				System.out.println(rpaTotalCopies);
				break;
			} else {
				continue;
			}
		}

		// 6. Verify if Sum of all Course prices matches with Purchase Amount - Without TestNG
		int courseSum = 0;

		for (int j = 0; j < courseCount; j++) {
			int coursePrice = jsonPath.getInt("courses[" + j + "].price");
			int courseCopies = jsonPath.getInt("courses[" + j + "].copies");
			courseSum += (coursePrice * courseCopies);
		}
		System.out.println(courseSum);

		if (puchaseAmount == courseSum) {
			System.out.println("Test case Passed");
		} else {
			System.out.println("Test case Failed");

		}

	}

}
