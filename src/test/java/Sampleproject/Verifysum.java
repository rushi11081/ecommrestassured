package Sampleproject;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Verifysum {

	@Test
	public void test()
	{
		JsonPath js=new JsonPath(Payload.course());

		int count=js.getInt("courses.size()");

		System.out.println(count);

		int sum=0;
		for(int i=0;i<count;i++)
		{
			int price1=js.getInt("courses["+i+"].price");
			int cpy=js.getInt("courses["+i+"].copies");
			int amt=price1*cpy;
			sum=sum+amt;
			System.out.println(sum);


		}

		Assert.assertEquals(sum,910);
	}
}
