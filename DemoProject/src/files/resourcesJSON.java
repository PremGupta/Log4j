package files;

public class resourcesJSON {

	public static String getResourse()
	{
		String res="/maps/api/place/nearbysearch/json";
		return res;
	}
	
	public static String postResource()
	{
		String res="/maps/api/place/add/json";
		return res;
	}
	
	public static String deleteResource()
	{
		String res="/maps/api/place/delete/json";
		return res;
	}
	
}
