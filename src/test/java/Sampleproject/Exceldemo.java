package Sampleproject;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class Exceldemo 
{

	public Object name;
	   
	   Object isbn;
	   Object aisle;
	   Object author;
	   

@Test
	public void get() throws IOException
	{
		
	   RestAssured.baseURI="http://216.10.245.166";
	   
	   String datapath="C:\\Users\\Rushikesh Patil\\eclipse-workspace2\\Rest\\Excel\\book.xlsx";
	
	   File excelfile=new File(datapath);
	   FileInputStream fin=new  FileInputStream(excelfile);
	   
	   XSSFWorkbook workbook=new XSSFWorkbook(fin);
	   
	   XSSFSheet sheet=workbook.getSheetAt(0);
	   
	   int rowcnt=sheet.getPhysicalNumberOfRows()-1;
	  
	   for(int i=0;i<rowcnt;i++)
	   {
	   
		   Row row=sheet.getRow(i);
		   
		   for(int j=0;j<row.getLastCellNum();j++)
		   {
	   DataFormatter dataformat=new DataFormatter();
		   					
		 name =dataformat.formatCellValue(sheet.getRow(i).getCell(j));
	  	   
	     	   
	   isbn =dataformat.formatCellValue(sheet.getRow(i).getCell(i));
	   
	    	   
	    aisle =dataformat.formatCellValue(sheet.getRow(i).getCell(j));
	
	    author =dataformat.formatCellValue(sheet.getRow(i).getCell(j));
	   
	JSONObject req=new JSONObject();
		
		req.put("name",name);
		
		req.put("isbn",isbn);
		
		req.put("aisle",aisle);
		
		req.put("author",author);
		
		   		
		String result=given().contentType(ContentType.JSON).accept(ContentType.JSON)
		.body(req.toJSONString()).when().post("/Library/Addbook.php").then()
		.statusCode(200).log().all().extract().response().asString();
		
		
		System.out.println("output is "+result);
		   }
	   }
		/*JsonPath js1=new JsonPath(result);
			
			String msg=js1.getString("Msg");
					
		System.out.println("output msg   is  "+msg);
		
			Assert.assertEquals(msg, "successfully added","it is validd book");*/
	   
	}
	
}
