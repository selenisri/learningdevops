import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.core.Is;
import org.json.simple.JSONObject;
import org.testng.asserts.SoftAssert;

public class loginapi {
	
	@Test(priority=12)
	public void test_1()
	{
		
		//Error message , with 400 should displayed
		
		Response response =  RestAssured.given().contentType("multipart/form-data")
				.multiPart("login","")
				.multiPart("password","")
				.multiPart("origin","")
				.when()
				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
		        int sta = response.statusCode();
	            Assert.assertEquals(sta, 400);
		
	}

	@Test(priority=1)
	public void test_2()
	{
		//Email ID is Empty , So Error Code 400 should be in response
		
		Response response =  RestAssured.given().contentType("multipart/form-data")
				.multiPart("login","")
				.multiPart("password","Zaiserve@123")
				.multiPart("origin","http://qatenant1.zaicrm.com")
				.when()
				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
		        int sta = response.statusCode();
	            Assert.assertEquals(sta, 400);
		
	}
	
//	@Test(priority=7)
//	public void test_3()
//	
//	{
//	//Valid Scenario , So 200 Should be in Response
//		
//		Response response =  RestAssured.given().contentType("multipart/form-data")
//				.multiPart("login","admin@fieldy.co")
//				.multiPart("password","Fieldy@123")
//				.multiPart("origin","http://qatenant1.zaicrm.com")
//				.when()
//				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
//		        int sta = response.statusCode();
//	            Assert.assertEquals(sta, 200);
//		
//		
//		
//			
//	}
	

	@Test(priority=3)
	public void test_4()
	{
		
		//Password is Empty , Validate the Error message is Invalid Password
	
		Response response =  RestAssured.given().contentType("multipart/form-data")
				.multiPart("login","admin@fieldy.co")
				.multiPart("password","")
				.multiPart("origin","http://qatenant1.zaicrm.com")
				.when()
				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
		
		// First get the JsonPath object instance from the Response interface
				JsonPath jsonPathEvaluator = response.jsonPath();

				// Then simply query the JsonPath object to get a String value of the node
				// specified by JsonPath: City (Note: You should not put $. in the Java code)
				String city = jsonPathEvaluator.get("data.password.message");

				// Let us print the city variable to see what we got
				System.out.println("Error is " + city);

				// Validate the response
				Assert.assertEquals(city, "Invalid Password");
		       
		
	}
	
	@Test(priority=4)
	public void test_5()
	{
		
		//Orgin is Empty , So Error Code 400 should be in response
	
		Response response =  RestAssured.given().contentType("multipart/form-data")
				.multiPart("login","admin@fieldy.co")
				.multiPart("password","Zaiserve@123")
				.multiPart("origin","")
				.when()
				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
		        int sta = response.statusCode();
	            Assert.assertEquals(sta, 400);
		
		
	}
	@Test(priority=5)
	public void test_6()
	{
		//Email ID is Invalid , So Errror code 400 should be in response
	
		Response response =  RestAssured.given().contentType("multipart/form-data")
				.multiPart("login","dmin@fieldy.co")
				.multiPart("password","Zaiserve@123")
				.multiPart("origin","http://qatenant1.zaicrm.com")
				.when()
				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
		// First get the JsonPath object instance from the Response interface
				JsonPath jsonPathEvaluator = response.jsonPath();

				// Then simply query the JsonPath object to get a String value of the node
				// specified by JsonPath: City (Note: You should not put $. in the Java code)
				String city = jsonPathEvaluator.get("data.login.message");

				// Let us print the city variable to see what we got
				System.out.println("Error is" + city);

				// Validate the response
				Assert.assertEquals(city, "Invalid Email");
		
	}
	
	@Test(priority=6)
	public void test_7()
	{
	
		//Password is Wrong so Error code should be in 400 in response
		
		Response response =  RestAssured.given().contentType("multipart/form-data")
				.multiPart("login","admin@fieldy.co")
				.multiPart("password","aiserve@123")
				.multiPart("origin","http://qatenant1.zaicrm.com")
				.when()
				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
		
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String city = jsonPathEvaluator.get("data.password.message");

		// Let us print the city variable to see what we got
		System.out.println("Error is " + city);

		// Validate the response
		Assert.assertEquals(city, "Invalid Password");
		
		
		
	}
	
	@Test(priority=2)
	public void test_8()
	{
		
		//origin is wrong , so the error message with 400 is displayed
	
		Response response =  RestAssured.given().contentType("multipart/form-data")
				.multiPart("login","admin@fieldy.co")
				.multiPart("password","Zaiserve@123")
				.multiPart("origin","http://batenant1.zaicrm.com")
				.when()
				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
		        int sta = response.statusCode();
	            Assert.assertEquals(sta, 401);
       
		
		
		
			
	}
 
	
	@Test(priority=8)
	public void test_9()
	{
		
		//Both Email and Password is wrong , so the error message with 400 is displayed
	
		Response response =  RestAssured.given().contentType("multipart/form-data")
				.multiPart("login","dmin@fieldy.co")
				.multiPart("password","Zaiserve@12")
				.multiPart("origin","http://qatenant1.zaicrm.com")
				.when()
				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
		        
		// First get the JsonPath object instance from the Response interface
				JsonPath jsonPathEvaluator = response.jsonPath();

				// Then simply query the JsonPath object to get a String value of the node
				// specified by JsonPath: City (Note: You should not put $. in the Java code)
				String city = jsonPathEvaluator.get("data.login.message");

				// Let us print the city variable to see what we got
				System.out.println("Error is " + city);

				// Validate the response
				Assert.assertEquals(city, "Invalid Email");
		
		
		
			
	}
	
//	@Test(priority=9)
//	public void test_10()
//	{
//		
//		//Password is wrong , so verify the error message with 400 is displayed
//	
//		Response response =  RestAssured.given().contentType("multipart/form-data")
//				.multiPart("login","admin@fieldy.co")
//				.multiPart("password","Zaiserve@12")
//				.multiPart("origin","http://qatenant1.zaicrm.com")
//				.when()
//				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
//		        String err_password = response.getBody().asString();
//		        int sta = response.statusCode();
//	            //Assert.assertEquals(sta, 400);
//		      
//		        assertTrue(err_password.contains("Invalid Password"));
//	            
//       
//		
//		
//		
//			
//	}
	
	@Test(priority=10)
	public void test_11()
	{
		
		//check the error message when the Email Field is null
		
		Response response =  (Response) RestAssured.given().contentType("multipart/form-data")
				.multiPart("login","")
				.multiPart("password","Zaiserve@123")
				.multiPart("origin","http://qatenant1.zaicrm.com")
				.when()
				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
		
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String city = jsonPathEvaluator.get("data.login.message");

		// Let us print the city variable to see what we got
		System.out.println("Error is " + city);

		// Validate the response
		Assert.assertEquals(city, "Enter Your Email");
       
		
		
		
			
	}
	
	
	@Test(priority=11)
	public void test_12()
	{
		
		//check the error message when the Email Field is null
		
		Response response =  (Response) RestAssured.given().contentType("multipart/form-data")
				.multiPart("login","admin@fieldy.co")
				.multiPart("password","")
				.multiPart("origin","http://qatenant1.zaicrm.com")
				.when()
				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
		
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String city = jsonPathEvaluator.get("data.password.message");

		// Let us print the city variable to see what we got
		System.out.println("Error is " + city);

		// Validate the response
		Assert.assertEquals(city, "Invalid Password");
       
		
		
		
			
	}
	
	
	@Test(priority=0)
	public void test_13()
	{
		
		//check the error message when the Email and Password of Tenant2 is Entered in Origin Tenant1
		
		Response response =  (Response) RestAssured.given().contentType("multipart/form-data")
				.multiPart("login","tenant1@gmail.com")
				.multiPart("password","Fieldy@123")
				.multiPart("origin","http://qatenant2.zaicrm.com")
				.when()
				.post("http://qaapigateway-saas.zaicrm.com/z1/api/user/login");
		
		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Then simply query the JsonPath object to get a String value of the node
		// specified by JsonPath: City (Note: You should not put $. in the Java code)
		String city = jsonPathEvaluator.get("data.message");

		// Let us print the city variable to see what we got
		System.out.println("Error is " + city);

		// Validate the response
		Assert.assertEquals(city, "Sorry! invalid information given");
       
		
		
		
			
	}
	
	
	
}
