package files;

import org.python.antlr.PythonParser.return_stmt_return;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	public static XmlPath rawToXML(Response res)
	{
		String responceString=res.asString();
		//System.out.println(responceString);
		XmlPath xml=new XmlPath(responceString);
		return xml;
	}
	
	public static JsonPath rawToJSON(Response res)
	{
		String responceString=res.asString();
		//System.out.println(responceString);
		JsonPath js=new JsonPath(responceString);
		return js;
	}

}
