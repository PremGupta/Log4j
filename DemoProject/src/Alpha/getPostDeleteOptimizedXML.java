package Alpha;

import org.apache.http.client.protocol.ResponseAuthCache;
import files.resourcesXML;
import files.ReusableMethods;

import org.apache.log4j.LogManager;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class getPostDeleteOptimizedXML {
	
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
	        get(resourcesXML.getResourse()).
	      
	//Assertion/validation
	then().assertThat().
	        statusCode(200).and().contentType(ContentType.XML).and().
	        extract().response();
	     /*   body("results[0].name" , equalTo("Sydney")).and().
	        body("results[0].place_id" , equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
	        header("server", "pablo");*/
}
	
	@org.testng.annotations.Test
	public void postData() throws IOException
	{
		String postdata=GenerateStringFromResource("C:/Users/Prem/git/Log4j/DemoProject/src/files/PostDataLoadXML.xml");

		//BaseURL or Host
		RestAssured.baseURI=prop.getProperty("HOST");
		
		//Adding data on site
		//Task 1- Grab the response (raw)
		Response res=given().
		        queryParam("key",prop.getProperty("KEY")).
		        body(postdata).
		 
		//Recourses
		when().
		        post(resourcesXML.postResource()).
		      
		//Assertion/validation
		then().assertThat().
		        statusCode(200).and().contentType(ContentType.XML).and().
		        extract().response();
		
		//convert raw response in String
	/*	String responceString=res.asString();
		System.out.println(responceString);
	
		//Task 2- Grab the Place id from response
		XmlPath xml=new XmlPath(responceString);*/
		
		XmlPath xml=ReusableMethods.rawToXML(res);
		String placeid=xml.get("PlaceAddResponse.place_id");
		System.out.println("Place id : "+placeid);
	
	
		/*//Task 3- Place this place id in Delete request
		
		String postdata1=GenerateStringFromResourceDATA("C:/Users/Prem/git/Log4j/DemoProject/src/files/PostDataLoadXML.xml");

		//Deleting place id
		given().
				queryParam("key",prop.getProperty("KEY")).
				body(postdata1).
		
		//Recourses
		when().
			   post(resourcesXML.deleteResource()).
				        
		then().
			   statusCode(200).and().contentType(ContentType.XML);*/
      }
	
	public static String GenerateStringFromResource(String path) throws IOException
	{
		//Return same xml in String format
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
/*	public static String GenerateStringFromResourceDATA(String path) throws IOException
	{
		//Return same xml in String format
		return new String(Files.readAllBytes(Paths.get(path)));
	}*/
	   
	}


