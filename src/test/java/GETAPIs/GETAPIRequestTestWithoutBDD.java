package GETAPIs;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


//Non BDD approach
public class GETAPIRequestTestWithoutBDD {

	RequestSpecification request;

	@BeforeTest
	public void setup() {
		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given();
		request.header("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc");
	}

	// https://gorest.co.in/
	@Test

	public void getAllUserAPITestforSetup() {
		// Request

		Response response = request.get("/public/v2/users/");
		int statuscode = response.statusCode();

		// Status code
		System.out.println("statuscode->" + statuscode);

		// Verification code
		Assert.assertEquals(statuscode, 200);

		// Status msg
		String statusMsg = response.statusLine();
		System.out.println("statusMsg->" + statusMsg);

		// Fetch the body from reps

		String PrettyStringResponse = response.getBody().asPrettyString().toString();
		System.out.println("PrettyStringResponse->" + PrettyStringResponse);

		// fetch header
		String ContentType = response.header("Content-Type");
		System.out.println("ContentType->" + ContentType);

		// Fetch all header
		List<Header> headerlist = response.headers().asList();
		System.out.println("headerlist->" + headerlist.size());
		// int i=0
		for (Header h : headerlist) {
			System.out.println("Header ->" + h.getName() + ":" + h.getValue());
		}

	}

	public void getAllUserAPITest() {
		// Request
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given().log().all();
		request.header("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc");
		Response response = request.get("/public/v2/users/");
		int statuscode = response.statusCode();

		// Status code
		System.out.println("statuscode->" + statuscode);

		// Verification code
		Assert.assertEquals(statuscode, 200);

		// Status msg
		String statusMsg = response.statusLine();
		System.out.println("statusMsg->" + statusMsg);

		// Fetch the body from reps

		String PrettyStringResponse = response.getBody().asPrettyString().toString();
		System.out.println("PrettyStringResponse->" + PrettyStringResponse);

		// fetch header
		String ContentType = response.header("Content-Type");
		System.out.println("ContentType->" + ContentType);

		// Fetch all header
		List<Header> headerlist = response.headers().asList();
		System.out.println("headerlist->" + headerlist.size());
		// int i=0
		for (Header h : headerlist) {
			System.out.println("Header ->" + h.getName() + ":" + h.getValue());
		}

	}

	@Test

	public void getAllUserWithQueryParamterAPITest() {
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc");
		// request.queryParam("name", "Bhagwanti Prajapat");
		Response response = request.get("/public/v2/users?name=Bhagwanti");
		int statuscode = response.statusCode();

		// Status code
		System.out.println("statuscode->" + statuscode);
		String PrettyStringResponse = response.getBody().asPrettyString().toString();
		System.out.println("PrettyStringResponse->" + PrettyStringResponse);
	}

	@Test
	public void getAllUserWithQueryParamterAPITestwith2PArm() {
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc");
		request.queryParam("name", "Bhagwanti");
		request.queryParam("status", "active");
		Response response = request.get("/public/v2/users");
		int statuscode = response.statusCode();

		// Status code
		System.out.println("statuscode->" + statuscode);

	}

	@Test
	public void getAllUserWithQueryParamter_WithHashMapAPITe1() {
		RestAssured.baseURI = "https://gorest.co.in";
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc");

		HashMap<String, String> quertParm = new HashMap<String, String>();
		quertParm.put("name", "Bhagwanti Menon IV");
		quertParm.put("status", "active");
		request.queryParams(quertParm);

		Response response = request.get("/public/v2/users");
		int statuscode = response.statusCode();

		// Status code
		System.out.println("statuscode->" + statuscode);
		String PrettyStringResponse = response.getBody().asPrettyString().toString();
		System.out.println("PrettyStringResponse->" + PrettyStringResponse);

	}

}
