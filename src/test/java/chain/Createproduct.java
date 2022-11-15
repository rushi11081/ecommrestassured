package chain;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.Testbase;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class Createproduct extends Testbase{
	
	String base;
	Testbase testbase;
	String prodid;
	
	@BeforeClass
	public void setup() throws IOException
	{
			
		testbase=new Testbase();
		
		base=prop.getProperty("baseurl");
		
	}
	
	@Test
    public void createproduct() 
    {
    	
	  
	 	  
     
    	RequestSpecification addproductreq=new RequestSpecBuilder().setBaseUri(base)
				.addHeader("Authorization",Datastore.token).build();
		
		RequestSpecification addproduct=given().log().all().spec(addproductreq)
				
				.param("productName","notepad")
				.param("productAddedBy",Datastore.userId)
				.param("productCategory","electronics")
				.param("productSubCategory","shirts")
				.param("productPrice","12500")
				.param("productDescription", "Addias Originals")
				.param("productFor","men")
				.multiPart("productImage",new File("C:\\Users\\Rushikesh Patil\\Desktop\\1.jpg"));
				
				
				String produresp=addproduct.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
				
     
	     JsonPath js=new JsonPath(produresp);
	     
	     prodid=js.get("productId");
	     
	     System.out.println(prodid);
	     
	     String msg=js.getString("message");
	     
	     
	     Assert.assertEquals(msg,"Product Added Successfully");
    	
    }
}
