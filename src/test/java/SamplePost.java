import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SamplePost {
	
	String  token;
	
	
	
	@Test
	
	public void apple()
	{
		
		//Error message , with 400 should displayed
		
		Response response =  RestAssured.given().contentType("multipart/form-data")
				.multiPart("login","admin@fieldy.co")
				.multiPart("password","Fieldy@123")
				.multiPart("origin","http://qatenant1.zaicrm.com")
				.when()
				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
		        int sta = response.statusCode();
	            System.out.println(sta);
	            JsonPath jsonPathEvaluator = response.jsonPath();
                token = jsonPathEvaluator.get("data.access_token");
                

		
	}
	
	
	
	
	
	
	
	
	
	
	
	@Test
	 public void ball()
	 {  
	  // Specify the base URL to the RESTful web service
	
			
		
      RestAssured.baseURI = "http://qaapigateway-saas.zaicrm.com/z1/api/user";
      
	  // Get the RequestSpecification of the request to be sent to the server
	  RequestSpecification httpRequest = RestAssured.given().header("Authorization", "Bearer "+token).header("Content-Type", "application/json");;
		
	  JSONObject requestParams = new JSONObject(); 
	  requestParams.put("first_name", "TQ123"); 
	  requestParams.put("email", "sriremse@fzago.com");
	  requestParams.put("role_id", "7");
	  httpRequest.body(requestParams.toJSONString());
	
	  
	  Response response = httpRequest.post("/user"); 
	  
	  String text= response.getBody().asString();
	 
	  System.out.println(text);
	  
	  System.out.println("The status received: " + response.statusLine());

	 }
	
	@Test
	public void catche()
	{
		
		  RestAssured.baseURI = "http://qaapigateway-saas.zaicrm.com/z1/api/user";
		  RequestSpecification httpRequest = RestAssured.given().header("Authorization", "Bearer "+token).header("Content-Type", "application/json");;
		  Response response = httpRequest.get("/user"); 
		  ResponseBody body = response.getBody();
		  String bodyAsString = body.asString();
		  System.out.println(bodyAsString);
		  JsonPath jsonPathEvaluator = response.jsonPath();
		  String tef =  jsonPathEvaluator.get("data.users.id[0]");
		  
		  System.out.println(tef);

		  

	}


}
