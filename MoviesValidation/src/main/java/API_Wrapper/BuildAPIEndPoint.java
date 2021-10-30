package API_Wrapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/*    -------------------------------------------------------------
 * 		AUTHOR: Sathiyaseelan Pandiyan
 * 		AUTHOR EMAIL: seelan.pandiyan@gmail.com 
 *    ------------------------------------------------------------- */

/* 
 *  Note: This Class has been created to retrieve the endpoints stored in the properties file by providing the matching Key's.* 
 * 
 */


public class BuildAPIEndPoint 
{
	static String propertiesLocation;	
	
	static
	{
		propertiesLocation = "./src/main/java/PropertyFiles/API_EndPoint.Properties";
	}
		
	
	public static String buildAPiEndPoint(String keySearch) throws IOException
	{
		String endPoint ="";
		try 
		{
			Properties properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(propertiesLocation);
			properties.load(fileInputStream);
			System.out.println("INFO: File Loaded Sucessfully");
			HashMap<String, String> hashMap = new HashMap<String, String>();
			File file = new File(propertiesLocation);
			if(file.exists())
			{				
				try 
				{
					for(Map.Entry<Object, Object> entry : properties.entrySet())
						hashMap.put(String.valueOf(entry.getKey()), String.valueOf(entry.getValue()));
					if((hashMap.containsKey("Total_Movie_Counts")) && (keySearch.contains("Total_Movie_Counts")))
						endPoint = hashMap.get(keySearch);
					else if((hashMap.containsKey("Movie_Name_Lists")) && (keySearch.contains("Movie_Name_Lists")))
						endPoint = hashMap.get(keySearch);
					else
						System.out.println("INFO: Keys not matched with the Properties File");
				}
				catch (Exception e) 
				{
					System.err.println("ERROR: Captured an Exception " +e.getMessage());
				}
			}
			else
			System.out.println("INFO: File not exists in the location");			
		} 
		catch (IOException e) 
		{
			System.err.println("ERROR: File not found at the location "+ e.getMessage());
		}	
		System.out.println("INFO: " + endPoint);
		return endPoint;
	}
	
	

}
