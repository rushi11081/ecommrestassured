package Sampleproject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

public class Test02 {

	
	@Test
	public void get3()
	{
		
		given().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("data.id[0]",equalTo(7)).log().all();
	}
}
