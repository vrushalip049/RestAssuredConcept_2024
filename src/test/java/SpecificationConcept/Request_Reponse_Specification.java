package SpecificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Request_Reponse_Specification {
	
	public static RequestSpecification user_req_Spec() {

		RequestSpecification requestspec = new RequestSpecBuilder().setBaseUri(
				"https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc")
				.build();
		return requestspec;
	}
	
	public static ResponseSpecification get_res_Spec_200_OK()
	{
		ResponseSpecification res_Spec_200_OK=new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectHeader("Server", "cloudflare")
		.build();
		
		return res_Spec_200_OK;
		
		
	}
	
	@Test

	public void getUser_With_Request_Spec1()

	{

		RestAssured.given().log().all().spec(user_req_Spec()).get("/public/v2/users/").
		
		then().log().all()
				.spec(get_res_Spec_200_OK());
	}

}
