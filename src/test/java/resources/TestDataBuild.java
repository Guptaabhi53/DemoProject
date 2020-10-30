package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	
	public AddPlace AddplacePayload(String name,String language, String address) {
	
	AddPlace p= new AddPlace();
	 p.setAccuracy(50);
	 p.setAddress(address);
	 p.setName(name);
	 p.setLanguage(language);
	 p.setWebsite("http://google.com");
	 p.setPhone_number("(+91) 983 893 3937");
	 
	 Location l = new Location();
	 l.setLat(-38.383494);
	 l.setLng(33.427362);
	 p.setLocation(l);
	 
	 List<String> t = new ArrayList<String>();
	 t.add("shoe park");
	 t.add("shop");
	 p.setTypes(t);
	 
	 return p; // returing object of addplace class

}

	public String deletePlacePayload(String place_id) {
		
		return "{\r\n" + 
				"    \"place_id\":\""+place_id+"\"\r\n" + 
				"}";
	}
}
