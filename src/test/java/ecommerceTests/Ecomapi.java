package ecommerceTests;

import Pojo.Loginrequest;
import Pojo.Loginresponse;
import Pojo.Orderdetail;
import Pojo.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
public class Ecomapi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		// login
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		
		Loginrequest req1=new Loginrequest();
		req1.setUserEmail("study@gmail.com");
		req1.setUserPassword("Sangola@123");
		
		RequestSpecification reqlogin=given().log().all().spec(req).body(req1);
		
		     Loginresponse loginresp=reqlogin.when().post("/api/ecom/auth/login").then().extract()
		    		 .response().as(Loginresponse.class);
		   
		     System.out.println(loginresp.getToken());
		     
		     String token=loginresp.getToken();
		     System.out.println(loginresp.getUserId());
		     String userId=loginresp.getUserId();
		     
		    
		     
		     // add product
		     
		 	
				RequestSpecification addproductreq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
						.addHeader("Authorization", token).build();
				
				RequestSpecification addproduct=given().log().all().spec(addproductreq)
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
			     
			     String prodid=js.get("productId");
			     System.out.println(prodid);
			     
			     
			     //  create-order
			     
			     RequestSpecification createorderreq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
							.addHeader("Authorization", token).setContentType(ContentType.JSON).build();
			     
			  Orderdetail orderdetail=new Orderdetail();
			 
			  orderdetail.setCountry("india");
			  orderdetail.setProductOrderedId(prodid);
			  
			  List<Orderdetail> orderDetailList = new ArrayList<Orderdetail> ();
				orderDetailList.add(orderdetail);
			     
			     Orders order=new Orders();
			     order.setOrders(orderDetailList);
			     
			     
			     			     
			     RequestSpecification createorder=given().log().all().spec(createorderreq).body(order);
			     
			     String responseAddOrder =createorder .when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
			     System.out.println(responseAddOrder);
			     

//			     JsonPath js12=new JsonPath(responseAddOrder);
//			     
//			     String orderid=js12.get("orders");
//			     System.out.println(orderid);
			     
			 	// get order
	    		 
	    		 RequestSpecification getorderreq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			    		 .addHeader("Authorization",token)
			    		 .build();
	    		 RequestSpecification getorder =given().log().all().spec(getorderreq).queryParam("id","636e2019d7778f57972df904");	 
 
	    		 String response=getorder.when().get("https://rahulshettyacademy.com/api/ecom/order/get-orders-details").then().log().all()
	    				 .extract().response().asString();
	    		 
	    		 System.out.println(response);
			     
			     
		// delete order
			     
			     RequestSpecification deleteProdBaseReq=	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
			    		 .addHeader("Authorization", token).setContentType(ContentType.JSON)
			    		 .build();

			    		 RequestSpecification deleteProdReq =given().log().all().spec(deleteProdBaseReq).pathParam("productId",prodid);

			    		 String deleteProductResponse = deleteProdReq.when().delete("/api/ecom/product/delete-product/{productId}").then().log().all().
			    		 extract().response().asString();

			    		 JsonPath js1 = new JsonPath(deleteProductResponse);

			    		 Assert.assertEquals("Product Deleted Successfully",js1.get("message"));
			     
			     
		
			    		 
	}

}
