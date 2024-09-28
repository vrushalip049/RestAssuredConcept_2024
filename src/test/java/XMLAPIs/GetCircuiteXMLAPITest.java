package XMLAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetCircuiteXMLAPITest {
	
	@Test
	//https://codebeautify.org/
	public void xmlTest()
	{
		RestAssured.baseURI = "http://ergast.com";
		RestAssured.given()
		.when()
		.get()
		.then()
		.extract().response();
	}

}
