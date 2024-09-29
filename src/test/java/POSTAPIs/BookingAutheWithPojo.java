package POSTAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;

import PUTAPIs.Auth;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;

import static io.restassured.RestAssured.*;//Imp
import static org.hamcrest.Matchers.*;

public class BookingAutheWithPojo {
	// POJO -plain old java object
	// can not extend any other class
	// oops encapsulation
	// private class vars -json body
	// public getter/setter
	//Serialization->java object to other format file,media,json/xml/text/pdf

	public static String getRandtomEmailId()
	{
		return "Apiautomatio"+System.currentTimeMillis()+"@mail.com";
	}
	
	@Test
	public void getBookingAuthTest_WithJson_String() {
		
	
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		Auth auth=new Auth("admin","password123");

		String token = given().contentType(ContentType.JSON)
				.body(auth).when()
				.post("/auth").then().assertThat().statusCode(200).extract().path("token");

		System.out.println("token->" + token);
		Assert.assertNotNull(token);
		

	}

}
