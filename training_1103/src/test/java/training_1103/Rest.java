package training_1103;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Rest {
	@Test(enabled=false)
	
	public void testcase()
	{
		Response res=RestAssured.get("http://localhost:3000/details/1");
		String responsebody=res.asString();
		System.out.println(responsebody);
		System.out.println(res.getStatusCode());
		System.out.println(res.getStatusLine());
		System.out.println(res.getHeaders());
		System.out.println(res.jsonPath().getString("Name"));
		
	}
	
	@Test(enabled=false)
	
	public void testcase2()
	{
		RestAssured.baseURI="http://localhost:3000";
		//RestAssured.given().get("/details/18").then().statusCode(200).log().all();
		//RestAssured.given().delete("/details/18").then().statusCode(200).log().all();
		RestAssured.given().get("/details/18").then().statusCode(404).log().all();
		RestAssured.given().contentType(ContentType.JSON).queryParam("username", "bindhu")
		.when().get("/details/21").then().statusCode(200).log().all();
	}
	
	@Test
	
	public void API_testcase()
	{
		RestAssured.baseURI="https://petstore.swagger.io/v2";
		RestAssured.given().queryParam("username", "devi").queryParam("password", "vijju").when().get("/user/login").then().statusCode(200).log().body();
		
	}

}
