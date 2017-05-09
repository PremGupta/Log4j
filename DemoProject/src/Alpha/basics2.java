package Alpha;
import org.apache.log4j.LogManager;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.apache.log4j.Logger;
import org.apache.logging.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.python.antlr.PythonParser.and_expr_return;

import cucumber.api.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class basics2 {
		
	@org.testng.annotations.Test
	public void postData()
	{
		//BaseURL or Host
		RestAssured.baseURI="https://maps.googleapis.com";
		
		//Adding data on site
		given().
		        queryParam("key","AIzaSyCYjsPBGT8WdUXuBDKu_rA5401Wwz-gs1o").
		        body("{"+
		        	  "\"location\": {"+
		            "\"lat\": -33.8669710,"+
		            "\"lng\": 151.1958750"+
		          "},"+
		          "\"accuracy\": 50,"+
		          "\"name\": \"Google Shoes!\","+
		          "\"phone_number\": \"(02) 9374 4000\","+
		          "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
		          "\"types\": [\"shoe_store\"],"+
		          "\"website\": \"http://www.google.com.au/\","+
		          "\"language\": \"en-AU\""+
		          "}").
		 
		//Resources
		when().
		        post("/maps/api/place/add/json").
		      
		//Assertion/validation
		then().assertThat().
		        statusCode(200).and().contentType(ContentType.JSON).and().
		        body("status",equalTo("OK"));
	
		//Create a place = response(Place id)
		//Delete Place = (Request - Place id)

	}

}
