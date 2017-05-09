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

public class basics {

	//private static Logger logger=LogManager.getLogger(basics.class.getName());
	//public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	/*	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Prem\\Downloads\\Study\\Selenium Files\\Zip Files\\chromedriver_win32/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com/");
		//Logger logger=Logger.getLogger("basics");
	
	        logger.info("This is info");
		    logger.debug("This is debug");
			logger.warn("This is warning");
			logger.error("this is error");
			logger.fatal("this is fatal");*/
		
		@org.testng.annotations.Test
		public void getData()
	{
		//BaseURL or Host
		RestAssured.baseURI="https://maps.googleapis.com";
		
		//Hitting site with parameters
		given().
		        param("location", "-33.8670522,151.1957362").
		        param("radius","500").
		        param("key","AIzaSyCYjsPBGT8WdUXuBDKu_rA5401Wwz-gs1o").log().all().and().
		 
		//Resources
		when().
		        get("/maps/api/place/nearbysearch/json").
		      
		//Assertion/validation
		then().assertThat().
		        statusCode(200).and().contentType(ContentType.JSON).and().
		        body("results[0].name" , equalTo("Sydney")).and().and().
		        body("results[0].place_id" , equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
		        header("server", "pablo");
	}
	}