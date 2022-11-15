package Sampleproject;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Test01Get {

	@Test
	public void get1()
	{
		
	Response response=get("https://reqres.in/api/users?page=2");
	System.out.println(response.getStatusCode());
	
	System.out.println(response.getBody().asString());
	
	System.out.println(response.getStatusLine());
	
	System.out.println(response.getHeader("Content-type"));
	
	int statuscode=response.getStatusCode();
	
	Assert.assertEquals(statuscode, 200);
	}
	
	@Test
	public void get2()
	{
		
		given().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("data.id[0]",equalTo(7));
	}
	
	
}
