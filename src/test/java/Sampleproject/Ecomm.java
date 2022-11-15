package Sampleproject;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class Ecomm {
	
	
	
	@Test
	public void getlogi()
	{
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		given()
		.header("Content-Type","application/json")
		.when()
		.body(" {\r\n"
				+ "  \"userEmail\":\"postman@gmail.com\",\r\n"
				+ "  \"userPassword\":\"Hello123@\"\r\n"
				+ "\r\n"
				+ "}")
		.post("/api/ecom/auth/login")
		.then()
		.assertThat().statusCode(200).log().all();
		
		
		
	}

}
