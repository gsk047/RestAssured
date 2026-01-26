package source;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
//import files.Payload;
import io.restassured.path.json.JsonPath;

public class CourseSum {

	// 6. Verify if Sum of all Course prices matches with Purchase Amount - With
	// TestNG

	@Test
	public void getCourseSum() {
		
		JsonPath jsonPath = new JsonPath(Payload.ComplexParseRequest());

		int courseSum = 0;
		
		int courseCount = jsonPath.getInt("courses.size()");

		for (int i = 0; i < courseCount; i++) {
			int coursePrice = jsonPath.getInt("courses[" + i + "].price");
			int courseCopies = jsonPath.getInt("courses[" + i + "].copies");
			courseSum += (coursePrice * courseCopies);
//			System.out.println(courseSum);
		}

		System.out.println(courseSum);
		
		int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
		Assert.assertEquals(courseSum, purchaseAmount);

	}

}
