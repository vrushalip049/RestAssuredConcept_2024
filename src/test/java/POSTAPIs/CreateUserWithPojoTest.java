package POSTAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserWithPojoTest {
	public static String getRandtomEmailId()
	{
		return "Apiautomatio"+System.currentTimeMillis()+"@mail.com";
	}

	@Test
	public void AddUserTest()
	{
		
		//Add user --POST
		RestAssured.baseURI="https://gorest.co.in";
		Users user =new Users("Vrushali", getRandtomEmailId(), "female", "active");
		
		int id=given().log().all()
		
		 .header("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc")
		 .contentType(ContentType.JSON)
		   .when().log().all()
		   .body(user)
		   .post("/public/v2/users/")
		   .then().log().all()
		   .assertThat()
		   .statusCode(201)
		   .and()
		   .body("name", equalTo(user.getName()))
			.extract().path("id");
		System.out.println("User id->"+id);
		
		//Get user --GET
	
		given().log().all()
		
				 .header("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc")
				   .when().log().all()
				   .get("/public/v2/users/"+id)
				   .then().log().all()
				   .assertThat()
				   .statusCode(200)
				   .and()
				   .body("name", equalTo("Vrushali"))
				   .body("name", equalTo(user.getName()))
				   .body("email", equalTo(user.getEmail()))
					.extract().path("id");
				System.out.println("User id->"+id);
	}

}
