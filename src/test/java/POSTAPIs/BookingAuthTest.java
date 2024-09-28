package POSTAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;//Imp
import static org.hamcrest.Matchers.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class BookingAuthTest {
	
	@Test
	public void getBookingAuthTokenTest()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String token=given()
		.contentType(ContentType.JSON)
		.body("{\r\n"
				+ "\"username\" : \"admin\",\r\n"
				+ "\"password\" : \"password123\"\r\n"
				+ "}")
		.when()
		.post("/auth")
		.then()
		.assertThat()
		.statusCode(200)
		.extract()
		.path("token");
		
		System.out.println("token->"+token);
		Assert.assertNotNull(token);
	}
	
	@Test
	public void getBookingAuthTokenTestwithJSONFile()
	{
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		
		String token=given().log().all()
		.contentType(ContentType.JSON)
		.body(new File("C:\\Users\\Admin\\eclipse-Automationworkspace\\RestAssuredConcept\\src\\test\\resources\\data\\basisAuth.json"))
				.when().log().all()
					.post("/auth")
						.then().log().all()
							.assertThat()
								.statusCode(200)
								 
									.extract()
										.path("token");
		
		System.out.println("token->"+token);
		Assert.assertNotNull(token);
	}

	
	//post > add a user> user if >123--asset (201,body)
	//get-->Get a user -> /user/123 --200-- userif =123

	@Test
	public void AddUSer()
	{
		RestAssured.baseURI="https://gorest.co.in";
		//create USer -POST
		int id=given().log().all()
		.contentType(ContentType.JSON)
		.body(new File("C:\\Users\\Admin\\eclipse-Automationworkspace\\RestAssuredConcept\\src\\test\\resources\\data\\AddUser.json"))
		.header("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc").when().log().all()
		.post("/public/v2/users/")
		.then().log().all()
		.assertThat()
		.statusCode(201)
		.body("name", equalTo("Vrushali4"))
		.extract()
		.path("id");
		
		System.out.println("id->"+id);
		Assert.assertNotNull(id);
		
		//get USer -GET
		
	given().log().all()
				.contentType(ContentType.JSON)
				
				.header("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc").when().log().all()
				.get("/public/v2/users/"+id)
				.then().log().all()
				.assertThat()
				.statusCode(200)
				.body("id", equalTo(id));
				
	}

}
