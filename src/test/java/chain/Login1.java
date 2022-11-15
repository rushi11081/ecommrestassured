package chain;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.Testbase;
import Pojo.Loginrequest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Login1 extends Testbase{
	Testbase testbase;
	String base;
  //  String token;
    Loginrequest lgnreq;

@BeforeMethod
public void setup() throws IOException
{
		
	testbase=new Testbase();
	
	base=prop.getProperty("baseurl");
	



}


    @Test
	public void logintest() throws FileNotFoundException
	{
    	
    	 //PrintStream loginlog=new PrintStream(new FileOutputStream("./src/main/resource/loginlog.txt"));
    	
		testbase=new Testbase();
    	lgnreq=new Loginrequest();
    	lgnreq.setUserEmail("study@gmail.com");
		lgnreq.setUserPassword("Sangola@123");
		
		
    	RequestSpecification req=new RequestSpecBuilder().setBaseUri(base)
				.setContentType(ContentType.JSON).build();
    	
   Datastore.token =given().log().all().spec(req).body(lgnreq)
    		
    	.when().post("/api/ecom/auth/login").then().extract().jsonPath().getString("token");
		
    	System.out.println(Datastore.token);
   
   Datastore.userId =given().log().all().spec(req).body(lgnreq)
   		
	    	.when().post("/api/ecom/auth/login").then().extract().jsonPath().getString("userId");
    	
   System.out.println(Datastore.userId);
   
    	Assert.assertEquals("Login Successfully","Login Successfully");
    	
	}
    
}
