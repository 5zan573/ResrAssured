package ResrAssured.API;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class Day2 {

	//@Test(priority =1)
	void testPostUsinghashMap() {
		
		HashMap hm=new HashMap();
		hm.put("name", "Scott");
		hm.put("location", "France");
		hm.put("phone", "1234567890");
		
		String coursearr[]= {"C","C++"};
		hm.put("course", coursearr);
		
		given()
		.contentType("application/json")
		.body(hm)
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("1234567890"))
		.body("course[0]", equalTo("C"))
		.body("course[1]", equalTo("C++"))
		.header("content-type", "application/json")
		.log().all();
	}

	//@Test(priority =1)
void testPostUsingJsonorg() {
		
		JSONObject data=new JSONObject();
		data.put("name", "Scott");
		data.put("location", "France");
		data.put("phone", "1234567890");
		
		String coursearr[]= {"C","C++"};
		data.put("course", coursearr);
		
		given()
		.contentType("application/json")
		.body(data.toString())
		
		.when()
		.post("http://localhost:3000/students")
		
		.then()
		.statusCode(201)
		.body("name", equalTo("Scott"))
		.body("location", equalTo("France"))
		.body("phone", equalTo("1234567890"))
		.body("course[0]", equalTo("C"))
		.body("course[1]", equalTo("C++"))
		.header("content-type", "application/json")
		.log().all();
	}

//@Test(priority =1)
void testPostUsingPOJOClass() {
	
	Pojo_postRequest data=new Pojo_postRequest();
	data.setName("Scott");
	data.setLocation("France");
	data.setPhone("12345678");
	
	String coursearr[]= {"React", "Java"};
	data.setCourse(coursearr);
	
	given()
	.contentType("application/json")
	.body(data)
	
	.when()
	.post("http://localhost:3000/students")
	
	.then()
	.statusCode(201)
	.body("name", equalTo("Scott"))
	.body("location", equalTo("France"))
	.body("phone", equalTo("12345678"))
	.body("course[0]", equalTo("React"))
	.body("course[1]", equalTo("Java"))
	.header("content-type", "application/json")
	.log().all();
}

@Test(priority =1)
void testPostUsingExternalJsonFile() throws FileNotFoundException {
	
	File f=new File(".\\body.json");
	FileReader fr=new FileReader(f);
	
	JSONTokener jt=new JSONTokener(fr);
	JSONObject data=new JSONObject(jt);

	
	given()
	.contentType("application/json")
	.body(data.toString())
	
	.when()
	.post("http://localhost:3000/students")
	
	.then()
	.statusCode(201)
	.body("name", equalTo("John"))
	.body("location", equalTo("india"))
	.body("phone", equalTo("1234567890"))
	.body("course[0]", equalTo("java"))
	.body("course[1]", equalTo("selenium"))
	.header("content-type", "application/json")
	.log().all();
}
	
	
	@Test(priority =2)
	void testDelete(){
		
		given()
		
		.when()
		.delete("http://localhost:3000/students/5a2f")
		
		.then()
		.statusCode(200)
		.log().all();	
	}
	
}
