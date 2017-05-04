package Beta;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class basics1 {

	//private static Logger logger=LogManager.getLogger(basics.class.getName());
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\Prem\\Downloads\\Study\\Selenium Files\\Zip Files\\chromedriver_win32/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.google.com/");*/
		Logger logger=Logger.getLogger("basics");
	
		    logger.info("This is info");
		    logger.debug("This is debug");
			logger.warn("This is warning");
			logger.error("this is error");
			logger.fatal("this is fatal");
		
	

	}

}
