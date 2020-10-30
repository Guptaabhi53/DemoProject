package resources;

//Instead of class ,need to write enum
//Enum is special class in java which has collection constant or methods

public enum APIResources {
	
	//Creating method
	
	AddPlaceAPI("/maps/api/place/add/json"),
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");
	private String resource;
	
//Constructor is definded by using class name
//One string is defind in menthod hence same is implemented in constructor as well
	APIResources(String resource)
	{
		this.resource=resource;
	}
	//to return the resource
	public String getresource() {
		
		return resource;
	}
}
