Feature: validating place API's

@AddPlace
Scenario Outline: Verify if place is being sucessfully added udsing AddplaceAPI
  Given Add place Payload with "<name>" "<language>" "<address>"
  When user calls "AddPlaceAPI" with "Post" http request
  Then the API call got sucess with status code 200
  And "status" in response is "OK"
  And "scope" in response is "APP"
  And verify place_Id created map to "<name>" using "getPlaceAPI"
  
  Examples:
  
 |name   |language  |address       |
 #|Abhinav|English   |Ambernath,1005|
 |Anand  |Hindi     |Kalyan,East   |
  
  
 @DeletePlace 
 Scenario: verify if delete functioanlity is working
 Given DeletePlace payload
 When user calls "deletePlaceAPI" with "Post" http request
 Then the API call got sucess with status code 200
 And "status" in response is "OK"
 
 