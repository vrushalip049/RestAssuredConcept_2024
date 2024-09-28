package SpecificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderTest {
	@Test
	public void getUser_With_Request_Spec()

	{
		RequestSpecification requestspec = new RequestSpecBuilder().setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc")
				.build();

		RestAssured.given().log().all().spec(requestspec).get("/public/v2/users/").then().log().all().statusCode(200);
	}

	public static RequestSpecification user_req_Spec() {

		RequestSpecification requestspec = new RequestSpecBuilder().setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc")
				.build();
		return requestspec;
	}

	@Test

	public void getUser_With_Request_Spec1()

	{

		RestAssured.given().log().all().spec(user_req_Spec()).get("/public/v2/users/").then().log().all()
				.statusCode(200);
	}
	
	@Test

	public void getUser_With_Request_Spec2()

	{

		RestAssured.given().log().all()
		.spec(user_req_Spec())
		.queryParam("name", "naveen")
		.queryParam("status", "active")
			.get("/public/v2/users/")
				.then().log().all()
				   	.statusCode(200);
	}

}
