package chain;

import org.junit.BeforeClass;
import static io.restassured.RestAssured.*;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class Basetest
{

	@BeforeClass
	public void setup()
	{
		
		requestspecification=new RequestSpecBuilder()
				             .setBaseUri("https://rahulshettyacademy.com")
				             .setBasePath("/api/ecom")
				             .addHeader("Accept","application/Json")
				             .build();
		
		
		responsespecification=new ResponseSpecBuilder()
				              .expectContentType("application/json; charset=utf-8")
				              .build();
		
	}
	
}
