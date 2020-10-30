package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	static RequestSpecification Request; // Making static variable(single instance) because it will prevent the request to be null on 2nd execution

	public RequestSpecification requestSpecification() throws IOException {
		// if (Request==null) - when multiple data set are executed then all data should be present on log file 
		if (Request==null) {
			//printstream is class and stream is object. new FileOutputStream will create file at runtime
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		Request = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")) // use this (getGlobalValue("baseUrl") method need to change as static method as below
				.addQueryParam("key", "qaclick123")
				//RequestLoggingFilter will be applied to our object(request)and it will log everything
				//logRequestTo its an method to where should i log my request /rs 
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();

		return Request;
		}
		return Request;
    }
      public static String getGlobalValue(String key) throws IOException {
    	  
    	  Properties prop = new Properties();//using property class ,we can scan any file which has perperties extension
    	  //FileInputStream - File location to read the file
    	  FileInputStream fis = new FileInputStream("C:\\Users\\Abhinav\\eclipse-workspace\\APIFramework\\src\\test\\java\\resources\\global.properties");
    	  //Integrating properties object and file input object by using .load
    	  prop.load(fis);
    	 return prop.getProperty(key);// Paramerized by using key ...instead of base url prop.getProperty("baseurl")
          }
      
      public String getJsonPath(Response response,String key) 
      {
    	  String respo=response.asString();
    	  JsonPath js = new JsonPath(respo);
    	 return js.get(key).toString();
    	  
      }
      
      
}





