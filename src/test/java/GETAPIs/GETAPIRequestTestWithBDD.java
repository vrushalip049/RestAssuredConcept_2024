package GETAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import  static io.restassured.RestAssured.*;//Imp
import  static org.hamcrest.Matchers.*;

import java.util.List;

public class GETAPIRequestTestWithBDD {
	
	@Test
	public void getProductTest()
	{
		//This is builder pattern
		given().log().all()
		   .when().log().all()
			  .get("https://fakestoreapi.com/products")
				.then().log().all()
					.assertThat()
						.and()
						   .contentType(ContentType.JSON)
					          .statusCode(200)//It is hard asseration if got 404 then prohram will termonate here
					              .header("Connection","keep-alive")
					                    .body("$.size()",equalTo(20));
		/*
		 * // .body("id",is(notEqualTonull())); //
		 * .body("title",hasItem("Mens cotton Jacket"));
		 */					
		
	}
	
	@Test
	public void GetUserAPITest()
	{
		RestAssured.baseURI="https://gorest.co.in";
		
		given().log().all()
		 .header("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc")
		   .when().log().all()
		   .get("/public/v2/users/")
		   .then().log().all()
		   .assertThat()
		   .statusCode(200)
		   .and()
		   .body("$.size()", equalTo(10));
	}

	
	@Test
	public void GetUserAPITestQueryPArm()
	{
		RestAssured.baseURI="https://gorest.co.in";
		
		given().log().all()
		.queryParam("name", "Bhagwanti")
		 .header("Authorization", "Bearer 5637cb7b5cd298ac786fd59b9f004d741f0af10cf59372288638f00ebea47bcc")
		   .when().log().all()
		   .get("/public/v2/users/")
		   .then().log().all()
		   .assertThat()
		   .statusCode(200)
		   .and()
		   .body("$.size()", equalTo(5));
	}
	
	//Json Path
	
	@Test
	public void GetProductAPI_WithExtract_JSONPATH_BodyTest()

	{
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response=given().log().all()
		.queryParam("limit", 5)
		
		   .when().log().all()
		   .get("/products");
		   
		JsonPath js=response.jsonPath();
		
		int firstProductID=js.getInt("[0].id");
		System.out.println("firstProductID->"+firstProductID);
		

		String firstProducttitle=js.getString("[0].title");
		System.out.println("firstProducttitle->"+firstProducttitle);
		
		float firstProductPrice=js.getFloat("[0].price");
		System.out.println("firstProductPrice->"+firstProductPrice);
		
		int firstProductRatingCount=js.getInt("[0].rating.count");
		System.out.println("firstProductRatingCount->"+firstProductRatingCount);
	}
	
	@Test
	public void GetProductAPI_WithExtract_JSONPATH_BodyTestWithArray()

	{
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response=given().log().all()
		.queryParam("limit", 10)
		
		   .when().log().all()
		   .get("/products");
		   
		JsonPath js=response.jsonPath();
		
		List<Integer> IDLIST=js.getList("id");//0 -4>-->5
		List<String> ProductTitleLIST=js.getList("title");
		List<Float> ProducRateLIST=js.getList("rating.rate");
		List<Integer> ProducCountLIST=js.getList("rating.count");
		
		for (int i=0;i<IDLIST.size();i++)
		{
			int id =IDLIST.get(i);
			String ProductTitle =ProductTitleLIST.get(i);
			float ProducRate =ProducRateLIST.get(i);
			int ProducCount =ProducCountLIST.get(i);
			System.out.println(id +":"+ProductTitle+":"+ProducRate+":"+ProducCount);
		}
	
		//java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.Float (java.lang.Integer and java.lang.Float are in module java.base of loader 'bootstrap'
		//System.out.println(id +":"+ProductTitle+":"+ProducRate+":"+ProducCount);
		/*
		 * "rating": { "rate": 3, ->>>>>>>>>>>>>>>>>>>>>>>rate is mentiopn as float bu value is int in respose 
		 * 
		 
		 * "count": 400
		 */
	}
	
	@Test
	public void GetProductAPI_WithExtract_JSONPATH_BodyTestWithArray1()

	{
		RestAssured.baseURI="https://fakestoreapi.com";
		
		Response response=given().log().all()
		.queryParam("limit", 10)
		
		   .when().log().all()
		   .get("/products");
		   
		JsonPath js=response.jsonPath();
		
		List<Integer> IDLIST=js.getList("id");//0 -4>-->5
		List<String> ProductTitleLIST=js.getList("title");
		//List<Object> ProducRateLIST=js.getList("rating.rate");
		List<Float> ProducRateLIST=js.getList("rating.rate",Float.class);
		List<Integer> ProducCountLIST=js.getList("rating.count");
		
		for (int i=0;i<IDLIST.size();i++)
		{
			int id =IDLIST.get(i);
			String ProductTitle =ProductTitleLIST.get(i);
			Object ProducRate =ProducRateLIST.get(i);
			int ProducCount =ProducCountLIST.get(i);
			System.out.println(id +":"+ProductTitle+":"+ProducRate+":"+ProducCount);
		}
	
		//java.lang.ClassCastException: class java.lang.Integer cannot be cast to class java.lang.Float (java.lang.Integer and java.lang.Float are in module java.base of loader 'bootstrap'
		//System.out.println(id +":"+ProductTitle+":"+ProducRate+":"+ProducCount);
		/*
		 * "rating": { "rate": 3, ->>>>>>>>>>>>>>>>>>>>>>>rate is mentiopn as float bu value is int in respose 
		 * 
		 
		 * "count": 400
		 */
	}
	
	@Test
	public void GetProductAPI_WithExtract_JSONPATH_BodyTestWithArray2()

	{
		RestAssured.baseURI="https://gorest.co.in";
		
		Response response=given().log().all()
		
		
		   .when().log().all()
		   .get("/public/v2/users/7421523");
		   
		JsonPath js=response.jsonPath();
		
		int id=js.getInt("id");
		String name=js.getString("name");
		System.out.println(id+name);
	
		
		
	}
	
	@Test
	public void GetProductAPI_WithExtract_JSONPATH_BodyTestWithArray3()

	{
		RestAssured.baseURI="https://gorest.co.in";
		
		int userID=given().log().all()
		
		
		   .when().log().all()
		   .get("/public/v2/users/7421523")
		   .then()
		   .extract().path("id");
		System.out.println("userID->"+userID);
		   
		
		
		
	}
	
	@Test
	public void GetProductAPI_WithExtract_JSONPATH_BodyTestWithArray4()

	{
		RestAssured.baseURI="https://gorest.co.in";
		
		int userID=given().log().all()
		
		
		   .when().log().all()
		   .get("/public/v2/users/7421523")
		   .then()
		   .extract().path("id");
		System.out.println("userID->"+userID);
		   
		
		
		
	}
	
	@Test
	public void GetProductAPI_WithExtract_JSONPATH_BodyTestWithArray5()

	{
		RestAssured.baseURI="https://gorest.co.in";
		
		Response  response=given().log().all()
		
		
		   .when().log().all()
		   .get("/public/v2/users/7421521");
		   
		   
		int id= response.then().extract().path("id");
		String email =response.then().extract().path("email");
		 
		System.out.println("userID->"+id);
		System.out.println("UserName->"+email);
		   
		
		
		
	}
	
	@Test
	public void GetProductAPI_WithExtract_JSONPATH_BodyTestWithArray6()

	{
		RestAssured.baseURI="https://gorest.co.in";
		
		Response  response=given().log().all()
		   .when().log().all()
		   .get("/public/v2/users/7421521")
		   .then()
		   .extract()
		   .response();
		   
		   
		int id= response.path("id");
		String email =response.path("email");
		 
		System.out.println("userID->"+id);
		System.out.println("UserName->"+email);
		   
		
		
		
	}
	
}
