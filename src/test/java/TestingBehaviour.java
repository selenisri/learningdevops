import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class TestingBehaviour {

	@Test
	public void Login()
	{
		RestAssured.given().baseUri("http://qaapigateway-saas.zaicrm.com/z1/api/user/").header("Content-Type","multipart/form-data")
		.multiPart("login","admin@fieldy.co")
		.multiPart("password","Fieldy@123")
		.multiPart("origin","http://qatenant1.zaicrm.com").when().post("login").prettyPrint();
	}
}
