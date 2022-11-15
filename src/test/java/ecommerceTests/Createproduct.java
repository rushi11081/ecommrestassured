package ecommerceTests;

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
    public void createproduct(ITestContext context) throws FileNotFoundException
    {
    	String token=(String) context.getSuite().getAttribute("Authorization");
    	
    	String userId=(String) context.getSuite().getAttribute("productAddedBy");
	  
	  PrintStream productlog=new PrintStream(new FileOutputStream("./src/main/resource/createproductlog.txt"));
	  
     
    	RequestSpecification addproductreq=new RequestSpecBuilder().setBaseUri(base)
				.addHeader("Authorization",token).build();
		
		RequestSpecification addproduct=given().log().all().spec(addproductreq)
				.filter(RequestLoggingFilter.logRequestTo(productlog))
				.filter(ResponseLoggingFilter.logResponseTo(productlog))
				.param("productName","notepad")
				.param("productAddedBy",userId)
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
