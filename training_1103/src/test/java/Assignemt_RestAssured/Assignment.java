package Assignemt_RestAssured;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import POJO_1503.POJOclass;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Assignment {
	
	

@Test()
public void createuser(ITestContext var1,ITestContext var2) throws JsonProcessingException 
{
	Pojoass obj= new Pojoass();
	obj.setUsername("jewsitha");
	obj.setFirstname("jeswi");
	obj.setLastname("ganga");
	obj.setPassword("jesu");
	obj.setEmail("jesi@abc.com");
	obj.setUserstatus("0");
	
	String uname=obj.getUsername();
	String firstname=obj.getFirstname();
	String lastname=obj.getLastname();
	String email=obj.getEmail();
	String status=obj.getUserstatus();
	String pwd=obj.getPassword();
	
	
	System.out.println(obj.getUsername());
	System.out.println(obj.getFirstname());
	System.out.println(obj.getLastname());
	System.out.println(obj.getPassword());
	System.out.println(obj.getEmail());
	System.out.println(obj.getUserstatus());
	ObjectMapper mapper=new ObjectMapper();
	String body=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	
	System.out.println(body);
	RestAssured.baseURI="https://petstore.swagger.io/v2";
	RestAssured.given().contentType(ContentType.JSON).body(body)
	.when().post("/user").then().statusCode(200).log().all();
	var1.setAttribute("USERNAME", uname);
	
	var2.setAttribute("PASSWORD", pwd);
	
}


@Test(dependsOnMethods="createuser")
public void modify(ITestContext var1) throws JsonProcessingException 
{
	String Username=var1.getAttribute("USERNAME").toString();
	
	Pojoass obj= new Pojoass();
	
	obj.setUsername("jewsitha");
	obj.setFirstname("jeswi");
	obj.setLastname("abc");
	obj.setPassword("jesu");
	obj.setEmail("jesi@abc.com");
	obj.setUserstatus("0");
	
	ObjectMapper mapper=new ObjectMapper();
	String body=mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
	System.out.println(body);
	RestAssured.baseURI="https://petstore.swagger.io/v2";
	RestAssured.given().contentType(ContentType.JSON)
	.body(body).when().put("/user/"+Username).then().statusCode(200).log().all();



}

@Test(dependsOnMethods="createuser")
public void login(ITestContext var1,ITestContext var2) throws JsonProcessingException 
{
	String Username=var1.getAttribute("USERNAME").toString();
	String pwd=var1.getAttribute("PASSWORD").toString();
	
	
	RestAssured.baseURI="https://petstore.swagger.io/v2";
	RestAssured.given().get("/user/login?username="+Username+"&password="+pwd).then().statusCode(200).log().all();



}
@Test(dependsOnMethods="login")
public void logout(ITestContext var1) throws JsonProcessingException 
{
	String Username=var1.getAttribute("USERNAME").toString();
	//String pwd=var1.getAttribute("PASSWORD").toString();
	
	
	RestAssured.baseURI="https://petstore.swagger.io/v2";
	RestAssured.given().get("/user/logout?username="+Username).then().statusCode(200).log().all();



}

@Test(dependsOnMethods="logout")
public void delete(ITestContext var1) throws JsonProcessingException 
{
	String Username=var1.getAttribute("USERNAME").toString();
	//String pwd=var1.getAttribute("PASSWORD").toString();
	
	
	RestAssured.baseURI="https://petstore.swagger.io/v2";
	RestAssured.given().get("/user/"+Username).then().statusCode(200).log().all();



}




}
