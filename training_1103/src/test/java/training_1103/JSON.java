package training_1103;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJO_1503.POJOclass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;





public class JSON {
	
	@Test(enabled=false)
	
	public void jsontestcase()
	{
		RestAssured.baseURI="http://localhost:3000";
		String reqbody="{\"Name\":\"abc\",\"Address\":\"xyz\",\"email\":\"abc@123.com\"}";
		RestAssured.given().contentType(ContentType.JSON).body(reqbody)
		.when().post("/details").then().statusCode(201).log().all();

}

@Test(enabled=false)
	
	public void jsontestcase2() throws IOException
	{
	    FileInputStream fis = new FileInputStream(".\\JSON\\abc.json");
		RestAssured.baseURI="http://localhost:3000";
		RestAssured.given().contentType(ContentType.JSON)
		.body(IOUtils.toString(fis, "UTF-8"))
		.post("/details").then().statusCode(201).log().all();

}

@Test(enabled=false)

public void jsonobjecttestcase() throws IOException
{
    JSONObject obj=new JSONObject();
    obj.put("Name","vidya");
    obj.put("Address","xyz");
    obj.put("email","xyz@abc.com");
    
	RestAssured.baseURI="http://localhost:3000";
	RestAssured.given().contentType(ContentType.JSON)
	.body(obj.toJSONString()).when()
	.post("/details").then().statusCode(201).log().all();

}

@Test (enabled=false)
public void jsonarraytestcase() throws IOException
{
    JSONObject obj=new JSONObject();
    obj.put("Name","vidya");
    obj.put("Address","xyz");
    obj.put("email","xyz@abc.com");
    
    JSONObject category=new JSONObject();
    category.put("id", 123);
    category.put("name", "efg");
    
    JSONObject tags=new JSONObject();
    tags.put("id", 13);
    tags.put("name", "efgh");
    
    
   obj.put("category", category);
   obj.put("tags", tags);
    
    JSONArray arr= new JSONArray();
    arr.add("kgf");
    arr.add("RRR");
    
    obj.put("photoUrl", arr);
    
	RestAssured.baseURI="http://localhost:3000";
	RestAssured.given().contentType(ContentType.JSON)
	.body(obj.toJSONString()).when()
	.post("/details").then().statusCode(201).log().all();

}
@Test (enabled = false)
public void assertontestcase(ITestContext testvariable)
{
    JSONObject obj=new JSONObject();
    obj.put("Name","vijju");
    obj.put("Address","xz");
    obj.put("email","xz@abc.com");
    
    
    
	RestAssured.baseURI="http://localhost:3000";
	Response resobj= RestAssured.given().contentType(ContentType.JSON)
	.body(obj.toJSONString()).when()
	.post("/details").then().statusCode(201).log().all().extract().response();
	
	String name=resobj.jsonPath().getString("Name");
	String id=resobj.jsonPath().getString("id");
	System.out.println(name);
	Assert.assertEquals(name, "vijju");
	testvariable.setAttribute("keyname", id);
	System.out.println(id);
	

}

@Test (enabled = false)
public void getdetails(ITestContext testvariable)
{
	String id=testvariable.getAttribute("Keyname").toString();
	System.out.println(id);
	

	RestAssured.baseURI="http://localhost:3000";
	RestAssured.given().get("/details/"+id).then().statusCode(200).log().all();
	
}

@Test
public void POJO() throws JsonProcessingException 
{
	POJOclass obj= new POJOclass();
	obj.setName("mani");
	obj.setAddress("xyz");
	obj.setEmail("abc@hvf.com");
	System.out.println(obj.getName());
	System.out.println(obj.getAddress());
	System.out.println(obj.getEmail());
	ObjectMapper mapper=new ObjectMapper();
	String body=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	
	System.out.println(body);
	RestAssured.baseURI="http://localhost:3000";
	RestAssured.given().contentType(ContentType.JSON).body(body)
	.when().post("/details").then().statusCode(201).log().all();
	
	
}


}