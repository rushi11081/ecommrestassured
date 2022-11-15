package ecommerceTests;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.Testbase;
import Pojo.Orderdetail;
import Pojo.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Createorder extends Testbase {

	String base;
	Testbase testbase;
	String prodid;
	
	@BeforeMethod
	public void setup() throws IOException
	{
			
		testbase=new Testbase();
		
		base=prop.getProperty("baseurl");
		
	}
	
	 @Test
     public void createorder(ITestContext context) throws FileNotFoundException
     {
    	  
    	  String token=(String) context.getSuite().getAttribute("Authorization");
    	  PrintStream orderlog=new PrintStream(new FileOutputStream("./src/main/resource/createorderlog.txt"));
    	  
		     RequestSpecification createorderreq=new RequestSpecBuilder().setBaseUri(base)
						.addHeader("Authorization",token)
								.setContentType(ContentType.JSON).build();
		     
		  Orderdetail orderdetail=new Orderdetail();
		 
		  orderdetail.setCountry("india");
		  orderdetail.setProductOrderedId("6371d14ad7778f57972fd5f1");
		  
		  List<Orderdetail> orderDetailList = new ArrayList<Orderdetail> ();
			orderDetailList.add(orderdetail);
		     
		     Orders order=new Orders();
		     order.setOrders(orderDetailList);
		     
		     
		     			     
		     RequestSpecification createorder=given().log().all().spec(createorderreq).body(order)
		    		 .filter(RequestLoggingFilter.logRequestTo(orderlog))
		    		 .filter(ResponseLoggingFilter.logResponseTo(orderlog));
		     
		     String responseAddOrder =createorder .when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
		     System.out.println(responseAddOrder);
    	 
     }
	
}
