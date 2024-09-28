package POSTAPIs;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;//Imp
import static org.hamcrest.Matchers.*;

import java.util.List;

public class OAuth2Test {

	// https://test.api.amadeus.com/
	// https://test.api.amadeus.com/v1/Security/outh2/token
	// https://github.com/samheyman/amadeus-developer-guides/blob/master/guides/getstarted.md
	/*
	 * curl \ -X POST \ -H "Content-Type: application/x-www-form-urlencoded" \
	 * https://test.api.amadeus.com/v1/security/oauth2/token \ -d
	 * "grant_type=client_credentials&client_id=PgtsC87KMeMAUTAmT985jrdOMNALe&client_secret=Qw12345erTy"
	 */

	/*
	 * { "type": "amadeusOAuth2Token", "username": "vrushalip049@gmail.com",
	 * "application_name": "Vrushali Patil", "client_id":
	 * "D3aGOKMCYxqAUzErxMImQHdJfGbeXcVG", "token_type": "Bearer", "access_token":
	 * "5bX8IINxRACHuLyHZX69bLyGjKbc", "expires_in": 1799, "state": "approved",
	 * "scope": "" }
	 */
	@Test
	public void getFlightInfoTest() {

		// 1.POST -get the access token
		RestAssured.baseURI = "https://test.api.amadeus.com";

		String access_token = given().log().all().header("Content-Type", "application/x-www-form-urlencoded")
				.formParam("grant_type", "client_credentials")
				.formParam("client_id", "D3aGOKMCYxqAUzErxMImQHdJfGbeXcVG")
				.formParam("client_secret", "v2PXAZIOy9AvvbLW").when().log().all().post("/v1/security/oauth2/token")
				.then().log().all().assertThat().statusCode(200).extract().path("access_token");

		System.out.println("access_token->" + access_token);

		/*
		 * curl -X GET \
		 * 'https://test.api.amadeus.com/v1/shopping/flight-destinations?origin=PAR&
		 * maxPrice=200'\ -H "Authorization: Bearer ApjU0sEenniHCgPDrndzOSWFk5mN"
		 * 
		 * 
		 * int id =given().log().all() .contentType(ContentType.JSON)
		 * .header("Authorization", "Bearer "+access_token).when().log().all()
		 * .get("v1/shopping/flight-destinations") .then().log().all() .assertThat()
		 * .statusCode(200) .body("id", equalTo(id));
		 */

		// Get flight info
		// This is builder pattern
		Response response = given().log().all().header("Authorization", "Bearer " + access_token)
				.queryParam("origin", "PAR").queryParam("maxPrice", "200").when().log().all()
				.get("/v1/shopping/flight-destinations");

		JsonPath js = response.jsonPath();
		String type = js.get("data[0].type");

		System.out.println("type->" + type);

		/*
		 * List<Float> total=js.get("$.price.total");
		 * 
		 * for (Float t:total) { System.out.println("t"+t); }
		 */
	}

	String access_token;

	@BeforeTest
	public void token() {
		RestAssured.baseURI = "https://test.api.amadeus.com";

		access_token = given().log().all().header("Content-Type", "application/x-www-form-urlencoded")
				.formParam("grant_type", "client_credentials")
				.formParam("client_id", "D3aGOKMCYxqAUzErxMImQHdJfGbeXcVG")
				.formParam("client_secret", "v2PXAZIOy9AvvbLW").when().log().all().post("/v1/security/oauth2/token")
				.then().log().all().assertThat().statusCode(200).extract().path("access_token");

		System.out.println("access_token->" + access_token);
	}

	@Test
	public void  getFlightInfoTest1()
	{
		
		//1.POST -get the access token
		
	
		Response response=given().log().all()
		.header("Authorization", "Bearer "+access_token)
		.queryParam("origin", "PAR")
		.queryParam("maxPrice", "200")
		   .when().log().all()
			  .get("/v1/shopping/flight-destinations");
				
		JsonPath js=response.jsonPath();
		String type=js.get("data[0].type");
		
		System.out.println("type->"+type);
		
		
}
}
