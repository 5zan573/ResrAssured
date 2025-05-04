package ResrAssured.API;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import java.util.HashMap;

import org.testng.annotations.Test;



public class Day1 {
	
	int id;
	
	@Test(priority = 1)
	void getUsers() {

		when()
		.get("https://reqres.in/api/users?page=2")
		
		.then()
		.statusCode(200)
		.body("page", equalTo(2))
		.log().all();
		
	}
	@Test(priority = 2)
	void createUser() {
		
		HashMap data=new HashMap();
		data.put("name","faizan");
		data.put("job","tester");
		
		id=given()
		.header("x-api-key","reqres-free-v1")
		.contentType("application/json")
		.body(data)
		
		.when()
		.post("https://reqres.in/api/users")
		.jsonPath().getInt("id");
		
		//.then()
		//.statusCode(201)
		//.log().all();
		
	}
	@Test (priority = 3, dependsOnMethods= {"createUser"})
	void updateUser() {
		
		HashMap data=new HashMap();
		data.put("name","Pablo");
		data.put("job","tester");
		
		given()
		   .header("x-api-key","reqres-free-v1")
		   .contentType("application/json")
		   .body(data)
		
		.when()
		   .put("https://reqres.in/api/users/"+id)
		
		.then()
		   .statusCode(200)
		   .log().all();
	}
	
	@Test(priority = 4)
	void deleteUser() {
		
		given()
		.header("x-api-key","reqres-free-v1")
		
		.when()
		.delete("https://reqres.in/api/users/"+id)
		
		.then()
		.statusCode(204)
		.log().all();
		
	}

}
