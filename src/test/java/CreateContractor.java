import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateContractor {
	
	public String token;
	
	@Test
	public void test_1()
	{
		
		//Error message , with 400 should displayed
		
		Response response =  RestAssured.given().contentType("multipart/form-data")
				.multiPart("login","admin@fieldy.co")
				.multiPart("password","Fieldy@123")
				.multiPart("origin","http://qatenant1.zaicrm.com")
				.when()
				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
		        int sta = response.statusCode();
	           // System.out.println(sta);
	            JsonPath jsonPathEvaluator = response.jsonPath();
                token = jsonPathEvaluator.get("data.access_token");
                

		
	}
	
	
	@Test
	public void test_2()
	{
		
		
		
	//	System.out.println(token);
		
		 Response res=RestAssured.given().header("Authorization", "Bearer "+token).
        post("http://qaapigateway-saas.zaicrm.com/z1/api/user/contractor");
		
		int hello= res.statusCode();
		Assert.assertEquals(hello, 400);
		
		
//		Response response =  RestAssured.given().contentType("multipart/form-data")
//				.multiPart("login","admin@fieldy.co")
//				.multiPart("password","Fieldy@123")
//				.multiPart("origin","http://qatenant1.zaicrm.com")
//				.when()
//				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
//		        int sta = response.statusCode();
//	            System.out.println(sta);
//	            JsonPath jsonPathEvaluator = response.jsonPath();
//                token = jsonPathEvaluator.get("data.access_token");
                

		
	}
	
	
	@Test
	public void test_3()
	{
		
		
		 Response res=RestAssured.given().header("Authorization", "Bearer "+token).
				 contentType("multipart/form-data").multiPart("name", "").
        post("http://qaapigateway-saas.zaicrm.com/z1/api/user/contractor");
		
		 JsonPath jsonPathEvaluator = res.jsonPath();
		 String errorone = jsonPathEvaluator.get("data.name.message");
		 Assert.assertEquals(errorone, "This is required field.");
		
	
	}
	
	@Test
	public void test_4()
	{
		//
		
		 Response res=RestAssured.given().header("Authorization", "Bearer "+token).
				 contentType("multipart/form-data").multiPart("name", "Twitter").multiPart("email","email").
        post("http://qaapigateway-saas.zaicrm.com/z1/api/user/contractor");
		 
		 JsonPath jsonPathEvaluator = res.jsonPath();
		 String errortwo = jsonPathEvaluator.get("data.email.message");
		
		//System.out.println(errortwo);
		 Assert.assertEquals(errortwo, "Invalid Email");
			
		
	
	}

	@Test
	public void test_5()
	{
		//fax max characters validation
		
		 Response res=RestAssured.given().header("Authorization", "Bearer "+token).
				 contentType("multipart/form-data").multiPart("name", "Twitter").multiPart("fax","741852741852741852741852741852741852741852741852741852741852741852741852").
        post("http://qaapigateway-saas.zaicrm.com/z1/api/user/contractor");
		 
		 JsonPath jsonPathEvaluator = res.jsonPath();
		 String errortwo = jsonPathEvaluator.get("data.fax.message");
		
		//System.out.println(errortwo);
		 Assert.assertEquals(errortwo, "Provided characters should not exceed than 25 characters");
			
		
	
	}
	
	@Test
	public void test_6()
	{
		//Phones max characters
		
		 Response res=RestAssured.given().header("Authorization", "Bearer "+token).
				 contentType("multipart/form-data").multiPart("name", "Twitter").multiPart("phone","741852741852741852741852741852741852741852741852741852741852741852741852").
        post("http://qaapigateway-saas.zaicrm.com/z1/api/user/contractor");
		 
		 JsonPath jsonPathEvaluator = res.jsonPath();
		 String errortwo = jsonPathEvaluator.get("data.phone.message");
		
		//System.out.println(errortwo);
		 Assert.assertEquals(errortwo, "The phone may not be greater than 12 characters.");
			
		
	
	}
	
	
	@Test
	public void test_7()
	{
		//Phones min characters validation
		
		 Response res=RestAssured.given().header("Authorization", "Bearer "+token).
				 contentType("multipart/form-data").multiPart("name", "Twitter").multiPart("phone","741").
        post("http://qaapigateway-saas.zaicrm.com/z1/api/user/contractor");
		 
		 JsonPath jsonPathEvaluator = res.jsonPath();
		 String errortwo = jsonPathEvaluator.get("data.phone.message");
		
		//System.out.println(errortwo);
		 Assert.assertEquals(errortwo, "Enter minimum 6 characters.");
			
		
	
	}
	
	@Test
	public void test_8()
	{
		//Phones should not in alphabets
		 Response res=RestAssured.given().header("Authorization", "Bearer "+token).
				 contentType("multipart/form-data").multiPart("name", "Twitter").multiPart("phone","abcdefgh").
        post("http://qaapigateway-saas.zaicrm.com/z1/api/user/contractor");
		 
		 JsonPath jsonPathEvaluator = res.jsonPath();
		 String errortwo = jsonPathEvaluator.get("data.phone.message");
		
		//System.out.println(errortwo);
		 Assert.assertEquals(errortwo, "Phone must be a number");
			
		
	
	}
	
	
	@Test
	public void test_9()
	{
		//contact person should not exceed 512 characters
		 Response res=RestAssured.given().header("Authorization", "Bearer "+token).
				 contentType("multipart/form-data").multiPart("name", "Twitter").multiPart("contact_person_name","A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any character limits). Unfortunately, I could not think of a quick way to do so on my macbook and I therefore sdddsss  I therefore sdddsssdd A while back I needed to count the amount of letters that a piece of text in an email template had (to avoid passing any character limits). Unfortunately, I could not think of a quick way to do so on my macbook and I therefore sdddsss  I therefore sdddsssdd").
        post("http://qaapigateway-saas.zaicrm.com/z1/api/user/contractor");
		 
		 JsonPath jsonPathEvaluator = res.jsonPath();
		 String errortwo = jsonPathEvaluator.get("data.contact_person_name.message");
		
		//System.out.println(errortwo);
		 Assert.assertEquals(errortwo, "Please enter the contact person name within 512 Characters");
			
		
	
	}
	
	

}
