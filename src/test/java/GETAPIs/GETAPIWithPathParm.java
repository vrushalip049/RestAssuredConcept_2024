package GETAPIs;

import static io.restassured.RestAssured.*;//Imp
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/*
"MRData": {
    "xmlns": "http://ergast.com/mrd/1.5",
    "series": "f1",
    "url": "http://ergast.com/api/f1/2016/circuits.json",
    "limit": "30",
    "offset": "0",
    "total": "21",---------------------------------------------------
    "CircuitTable": {
        "season": "2016",
        "Circuits": [
            {
                "circuitId": "alb.ert_park",
                "url": "http://en.wikipedia.org/wiki/Melbourne_Grand_Prix_Circuit",
                "circuitName": "Albert Park Grand Prix Circuit",
                "Location": {
                    "lat": "-37.8497",
                    "long": "144.968",
                    "locality": "Melbourne",
                    "country": "Australia"
                }
            },*/
public class GETAPIWithPathParm {
//http://ergast.com/api/f1/2016/circuits.json
	@Test
	public void GetProductAPI_WithExtract_JSONPATH_BodyTest()

	{
		// Query vs Path
		// <k,v> <Any key ,value>>
		// ? /
		RestAssured.baseURI = "http://ergast.com";

		given().log().all().pathParam("year", "2016").when().log().all().get("/api/f1/{year}/circuits.json").then()
				.assertThat().statusCode(200).body("MRData.CircuitTable.season", equalTo("2016"));

	}

	// http://ergast.com/api/f1/2016/circuits.json
	@Test
	public void GetProductAPI_WithExtract_JSONPATH_BodyTest2()

	{
		// Query vs Path
		// <k,v> <Any key ,value>>
		// ? /
		RestAssured.baseURI = "http://ergast.com";

		given().log().all().pathParam("year", "2016").when().log().all().get("/api/f1/{year}/circuits.json").then()
				.assertThat().statusCode(200).body("MRData.CircuitTable.season", equalTo("2016")).and()
				.body("MRData.CircuitTable.Circuits.circuitId", hasSize(21));

	}

	// 2017 20
	// 2016 21
	// 1966 9

	@DataProvider
			public Object[][] GetCircuiteYearData()
			{
			return new Object[][]
					{ {"2016",21},
				      {"2017",20},
				      {"2023",22}
				};
			}

	@Test(dataProvider="GetCircuiteYearData")
	public void GetProductAPI_WithExtract_JSONPATH_DataProvider(String seasonyear,int totalcircuite)

	{
		// Query vs Path
		// <k,v> <Any key ,value>>
		// ? /
		RestAssured.baseURI = "http://ergast.com";

		given().log().all().pathParam("year", seasonyear).when().log().all().get("/api/f1/{year}/circuits.json").then()
				.assertThat().statusCode(200).body("MRData.CircuitTable.season", equalTo(seasonyear)).and()
				.body("MRData.CircuitTable.Circuits.circuitId", hasSize(totalcircuite));

	}

}
