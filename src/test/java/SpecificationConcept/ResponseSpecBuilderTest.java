package SpecificationConcept;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import  static org.hamcrest.Matchers.*;

public class ResponseSpecBuilderTest {

	public static ResponseSpecification get_res_Spec_200_OK()
	{
		ResponseSpecification res_Spec_200_OK=new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectHeader("Server", "cloudflare")
		.build();
		
		return res_Spec_200_OK;
		
		
	}
	
	public static ResponseSpecification get_res_Spec_200_OKWithBody()
	{
		ResponseSpecification res_Spec_200_OK=new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectHeader("Server", "cloudflare")
		.expectBody("$.size()",equalTo(5))
		.expectBody("id",hasSize(5))
		.build();
		
		return res_Spec_200_OK;
		
		
	}
	
	public static ResponseSpecification get_res_Spec_401_AuthFail()
	{
		ResponseSpecification res_Spec_401_AuthFail=new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(401)
		.expectHeader("Server", "cloudflare")
		.build();
		
		return res_Spec_401_AuthFail;
		
		
	}
	
	
	@Test
	
	public void get_res_Spec_200_OK_Test()
	{
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
		.queryParam("name", "Bhagwanti")
		 .header("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc")
		   .when().log().all()
		   .get("/public/v2/users/")
		   .then().log().all()
		   .spec(get_res_Spec_200_OKWithBody());
	}
	
@Test
	
	public void get_res_Spec_401_authFail_Test()
	{
		RestAssured.baseURI = "https://gorest.co.in";
		given().log().all()
		.queryParam("name", "Bhagwanti")
		 .header("Authorization", "Bearer 58ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc")
		   .when().log().all()
		   .get("/public/v2/users/")
		   .then()
		   .spec(get_res_Spec_401_AuthFail());
	}
	
	
}
