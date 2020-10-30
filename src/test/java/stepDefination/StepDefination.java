package stepDefination;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	RequestSpecification req;
	ResponseSpecification Resp;
	Response response;
	static String place_Id;
	TestDataBuild data = new TestDataBuild();// creating object of testdata class to access addplace payload

	@Given("Add place Payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		
	 req = given().spec(requestSpecification()).body(data.AddplacePayload(name,language,address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method)  {
		
		//APIResources.valueOf(resource) calls the constructor of enum class
		APIResources resourceAPI=APIResources.valueOf(resource); //Like creating an object of enum
		System.out.println(resourceAPI.getresource());
		
		Resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if(method.equalsIgnoreCase("POST"))
		response = req.when().post(resourceAPI.getresource()).then().spec(Resp).extract().response();
		else if(method.equalsIgnoreCase("GET"))
		response = req.when().get(resourceAPI.getresource()).then().spec(Resp).extract().response();
	}

	@Then("the API call got sucess with status code {int}")
	public void the_api_call_got_sucess_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
	}

	@Then("{string} in response is {string}")
	public void in_response_is(String keyValue, String ExpectedValue) {
		// Write code here that turns the phrase above into concrete actions
		
		assertEquals(getJsonPath(response,keyValue), ExpectedValue);
    }

	@Then("verify place_Id created map to {string} using {string}")
	public void verify_place_id_created_map_to_using(String Expectedname,String resource) throws IOException {
		
		  place_Id=getJsonPath(response,"place_id");
		req = given().spec(requestSpecification()).queryParam("place_id", place_Id);
		user_calls_with_http_request(resource,"GET");
		String ActualName=getJsonPath(response,"name");
		assertEquals(ActualName,Expectedname);
		
	}
	
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
		req = given().spec(requestSpecification()).body(data.deletePlacePayload(place_Id));
		
	}
	
}
