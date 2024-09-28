package PUTAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateUserTest {
	
	//Create user-- POST -->userId
	//update user --PUT-->userId
	
	public static String getRandtomEmailId()
	{
		return "Apiautomatio"+System.currentTimeMillis()+"@mail.com";
	}
	
	@Test
	public void updateUserTest()
	{
		RestAssured.baseURI="https://gorest.co.in";
		User user =new User("Vrushali", getRandtomEmailId(), "feamale", "active");
		
		Response response=RestAssured.given()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc")
		.body(user)
		.post("/public-api/users");
		
		/*
		 * JsonPath js=response.jsonPath(); int id =js.getInt("id");
		 * 
		 * System.out.println("id is ->"+id);
		 */
		
		
		
	}

}
