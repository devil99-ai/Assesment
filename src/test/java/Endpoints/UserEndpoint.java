package Endpoints;
import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import org.testng.Assert;

import Payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//This is user enpointclass created for CRUD operations for USER API

public class UserEndpoint {
	
	public static Response createUser(User payload){
		
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.when().log().all().body(payload)
		.post(Routes.post_url);
		
		return response;
	}
	
	public static Response readUser(String username){
		
		Response response=given().pathParam("username", username)
		.when().log().all()
		.get(Routes.get_url);
		
		return response;
	}
	public static Response updateUser(String username,User payload){
		
		Response response=given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON).pathParam("username", username)
		.when().body(payload)
		.put(Routes.update_url);
		
		return response;
	}
	
	public static Response deleteUser(String username){
		
		Response response=given().pathParam("username", username)
		.when()
		.delete(Routes.delete_url);
		
		return response;
	}
	
	
	

}

