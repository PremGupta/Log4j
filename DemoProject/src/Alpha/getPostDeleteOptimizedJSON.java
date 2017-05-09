package Alpha;

import org.apache.http.client.protocol.ResponseAuthCache;
import files.resourcesJSON;
import files.PostDataLoadJSON;

import org.apache.log4j.LogManager;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.python.antlr.PythonParser.and_expr_return;
import org.testng.annotations.BeforeTest;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class getPostDeleteOptimizedJSON {
	
	Properties prop=new Properties();
	
	@BeforeTest
	public void properties() throws IOException
	{
		
		 FileInputStream fis=new FileInputStream("C:/Users/Prem/git/Log4j/DemoProject/src/files/env.properties");
		 prop.load(fis);
	}
		
	@org.testng.annotations.Test
	public void getData()
{
	//BaseURL or Host
	RestAssured.baseURI=prop.getProperty("HOST");
	
	//Hitting site with parameters
	given().
	        param("location", "-33.8670522,151.1957362").
	        param("radius","500").
	        param("key",prop.getProperty("KEY")).
	 
	//Recourses
	when().
	        get(resourcesJSON.getResourse()).
	      
	//Assertion/validation
	then().assertThat().
	        statusCode(200).and().contentType(ContentType.JSON).and().
	        body("results[0].name" , equalTo("Sydney")).and().
	        body("results[0].place_id" , equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
	        header("server", "pablo");
}
	
	@org.testng.annotations.Test
	public void postData()
	{

		//BaseURL or Host
		RestAssured.baseURI=prop.getProperty("HOST");
		
		//Adding data on site
		//Task 1- Grab the response (raw)
		Response res=given().
		        queryParam("key",prop.getProperty("KEY")).log().all().and().
		        body(PostDataLoadJSON.getPostData()).
		 
		//Recourses
		when().
		        post(resourcesJSON.postResource()).
		      
		//Assertion/validation
		then().assertThat().
		        statusCode(200).and().contentType(ContentType.JSON).and().
		        body("status",equalTo("OK")).
		        extract().response();
		
		//convert raw response in String
		String responceString=res.asString();
		System.out.println(responceString);
	
		//Task 2- Grab the Place id from response
		JsonPath js=new JsonPath(responceString);
		String placeid=js.get("place_id");
		System.out.println(placeid);
	
	
		//Task 3- Place this place id in Delete request
		//Deleting place id
		given().
				queryParam("key",prop.getProperty("KEY")).
				body("{"+
				"\"place_id\":\""+placeid+"\""+
				"}").
		
		//Recourses
		when().
			   post(resourcesJSON.deleteResource()).
				        
		then().
			   statusCode(200).and().contentType(ContentType.JSON).and().
			   body("status",equalTo("OK"));
      }	      
	   
	}


