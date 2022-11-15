package Sampleproject;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Dynamicjsin {

	@Test
	public void addbook()
	{
		RestAssured.baseURI="http://216.10.245.166";
	String response=given().header("Content-Type","application/json").body(Payload.addbook())
		.when().post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
	
	  System.out.println(response);
	  
	  JsonPath js=new JsonPath(response);
	  String id=js.getString("ID");
	  System.out.println("ID IS " +id);
	  
	String res=given().log().all().header("Content-Type","application/json").when().get("Library/GetBook.php?ID=id").then().assertThat()
	  .statusCode(200).extract().response().asString();
	
	System.out.println(res);
	}
	

	@Test
	public void getbook()
	{
		RestAssured.baseURI="http://216.10.245.166";
	String response=given().header("Content-Type","application/json")
		.when().get("Library/GetBook.php?ID=ghfd987").then().assertThat().statusCode(200).extract().response().asString();
	
	  System.out.println(response);
	}
	
	@Test
	public void getbookbyauthor()
	{
		RestAssured.baseURI="http://216.10.245.166";
	String response=given().header("Content-Type","application/json")
		.when().get(" /Library/GetBook.php?AuthorName=John foe").then().assertThat().statusCode(200).extract().response().asString();
	
	  System.out.println(response);
	}
	
	
}
