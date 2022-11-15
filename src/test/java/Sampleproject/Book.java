package Sampleproject;

import io.restassured.path.json.JsonPath;

public class Book {

	
	public static void main(String[] args)
	{
		
		JsonPath js=new JsonPath(Payload.course());
		
		int count=js.getInt("courses.size()");
		
		System.out.println(count);
		
		int totalamout=js.getInt("dashboard.purchaseAmount");
		System.out.println(totalamout);
		
		String firsttitle=js.getString("courses[0].title");
		System.out.println(firsttitle);
		
		for(int i=0;i<count;i++)
		{
			String coursetitle=js.getString("courses["+i+"].title");
			System.out.println(js.get("courses["+i+"].price").toString());
			System.out.println(coursetitle);
		}
		
		System.out.println("print no of copies sold by RPA");
		
		for(int i=0;i<count;i++)
		{
			String coursetitle=js.getString("courses["+i+"].title");
			
			if(coursetitle.equalsIgnoreCase("RPA"))
			{
				int copies=js.get("courses["+i+"].copies");
				System.out.println(copies);
				break;
			}
		}
		
		System.out.println("verify amount ");
		
		int sum=0;
		for(int i=0;i<count;i++)
		{
		      int price1=js.getInt("courses["+i+"].price");
		      int cpy=js.getInt("courses["+i+"].copies");
		      int amt=price1*cpy;
		       sum=sum+amt;
		        System.out.println(sum);
		        
		        if(sum==totalamout)
		        {
		        	System.out.println("pass");
		        }
		        else
		        {
		        	System.out.println("fail");
		        }
		}
		
		
	}
	
}
