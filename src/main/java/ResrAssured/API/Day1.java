package ResrAssured.API;

import org.testng.annotations.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;  
import org.hamcrest.Matchers.*;



public class Day1 {
	
	@Test
	void getUsers() {
		
		given()
		
		.when()
		.get("https://reqres.in/api/users?page=2")
		
		.then()
		.statusCode(200)
		
	}
	

}
