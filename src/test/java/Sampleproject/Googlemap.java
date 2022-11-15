package Sampleproject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;
public class Googlemap {

	@Test
	public void test()
	{
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
	String response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Payload.addplace()).when().log().all().post("/maps/api/place/add/json").then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		         .header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
	
	     System.out.println(response);
	     
	     JsonPath js=new JsonPath(response);
      String result=js.getString("place_id");
      
      System.out.println(result);
      //Assert.assertEquals(result, "OK");
      
      
       given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
        .body("{\r\n"
        		+ "\r\n"
        		+ "\"place_id\": \""+result+"\",\r\n"
        		+ "\"address\": \"29, side layout, cohen 09\",\r\n"
        		+ "\"key\":\"qaclick123\"\r\n"
        		+ "\r\n"
        		+ "} ").when().put("/maps/api/place/update/json")
        
                .then().statusCode(200).body("msg",equalTo("Address successfully updated"));    
      
	}
}
