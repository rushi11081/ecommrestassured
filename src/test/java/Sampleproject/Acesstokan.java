package Sampleproject;
import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class Acesstokan {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.firefoxdriver().setup();;
		
		WebDriver driver= new FirefoxDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("srinath19830");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("password");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		String url=driver.getCurrentUrl();
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		System.out.println(code);		
		
		
		
		String accesstoken=given()
		.queryParam("code",code)
		.queryParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type","authorization_code")
		.when().post("https://www.googleapis.com/oauth2/v4/token").asString();

		JsonPath js=new JsonPath(accesstoken);
		String token=js.getString("access_token");
		
	String response=given().queryParam("access_token",token)
		.when().get("https://rahulshettyacademy.com/getCourse.php").asString();
		
	System.out.println(response);
	}

}
