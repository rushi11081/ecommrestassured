package Sampleproject;

import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class Test_Post {

	
	@Test
	public void post()
	{
//		Map<String,Object> map=new HashMap<String,Object>();
//		map.put("name","raghav");
//		map.put("job","qa");
//		
//		System.out.println(map);
		
		JSONObject req=new JSONObject();
		req.put("name","raghav");
		req.put("job","qa");
		System.out.println(req);
		
		System.out.println(req.toJSONString());
		
		given()
		.header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(req.toJSONString())
		.when().post("https://reqres.in/api/users").then().statusCode(201);
		
		
	}
	@Test
	public void test2put()
	{
		JSONObject req=new JSONObject();
		req.put("name","raghav");
		req.put("job","HR");
		System.out.println(req);
		
		System.out.println(req.toJSONString());
		
		given()
		.header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(req.toJSONString())
		.when().put("https://reqres.in/api/users/2").then().statusCode(200).log().all();
		
	}
	
	
	@Test
	public void test3pATCH()
	{
		JSONObject req=new JSONObject();
		req.put("name","raghav");
		req.put("job","HR");
		System.out.println(req);
		
		System.out.println(req.toJSONString());
		
		given()
		.header("Content-Type","application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(req.toJSONString())
		.when().patch("https://reqres.in/api/users/2").then().statusCode(200).log().all();
		
	}
	
	
	@Test
	public void test4DELETE()
	{
		
		when().delete("https://reqres.in/api/users/2").then().statusCode(204).log().all();
	   
		
		
	}
	
	
	
	
}
