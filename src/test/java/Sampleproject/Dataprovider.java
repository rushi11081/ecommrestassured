package Sampleproject;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Dataprovider {

	
	@Test(dataProvider="booksname")
	public void addbook(String isbn,String aisle)
	{
		RestAssured.baseURI="http://216.10.245.166";
	String response=given().header("Content-Type","application/json").body("{\\r\\n\"\r\n"
			+ "				+ \"\\r\\n\"\r\n"
			+ "				+ \"\\\"name\\\":\\\"Learn Appium Automation with rn\\\",\\r\\n\"\r\n"
			+ "				+ \"\\\"isbn\\\":\\\"\"+isbn+\"\\\",\\r\\n\"\r\n"
			+ "				+ \"\\\"aisle\\\":\\\"\"+aisle+\"\\\",\\r\\n\"\r\n"
			+ "				+ \"\\\"author\\\":\\\"fg\\\"\\r\\n\"\r\n"
			+ "				+ \"}")
		.when().post("/Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();
	
	  System.out.println(response);
	  
//	  JsonPath js=new JsonPath(response);
//	  String id=js.getString("ID");
//	  System.out.println("ID IS " +id);
//	  
//	String res=given().log().all().header("Content-Type","application/json").when().get("Library/GetBook.php?ID=id").then().assertThat()
//	  .statusCode(200).extract().response().asString();
//	
//	System.out.println(res);
	}
	
	
	
	@DataProvider(name="booksname")
	public Object[][] getbook()
	{
		
		return new Object[][] {{"afg","1567"},{"bdf","333"},{"chjj","12674"}};
		
	}
}
