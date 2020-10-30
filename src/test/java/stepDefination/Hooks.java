package stepDefination;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	
	@Before ("@DeletePlace")
	public void beforeScenario() throws IOException {
		
		
		StepDefination m = new StepDefination();
		if (StepDefination.place_Id==null)// Placeid is static vaiable so we can call by class name as well
		{
		m.add_place_payload_with("Abhinav", "Hindi", "Ambernath");
		m.user_calls_with_http_request("AddPlaceAPI", "POST");
		m.verify_place_id_created_map_to_using("Abhinav", "getPlaceAPI");
	    }
   }

}
