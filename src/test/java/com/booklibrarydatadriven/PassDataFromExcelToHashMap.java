package com.booklibrarydatadriven;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.readfile.ReadFile;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PassDataFromExcelToHashMap {

	public static void main(String[] args) throws IOException {
		
		ReadFile rd=new ReadFile();//Here we call class object to read data
		ArrayList arr=rd.getData("RestAddBook","RestAssured");
		//Convert JSON payload into HashMap and create book
			HashMap<String,Object> jsonAsMap =new HashMap<>();
			jsonAsMap.put("name", arr.get(1));
			jsonAsMap.put("isbn", arr.get(2));
			jsonAsMap.put("aisle", arr.get(3));
			jsonAsMap.put("author", arr.get(4));
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			String response = given().header("Content-Type", "application/json")
					.body(jsonAsMap)
					.when().post("Library/Addbook.php")
					.then().log().all().statusCode(200).extract().response().asString();
			JsonPath js = new JsonPath(response);
			String expMsg = "successfully added";
			String ID = js.get("ID");
			System.out.println("Added Book ID is: " +ID);
			String actualMessage = js.get("Msg");
			System.out.println(actualMessage);
			
			//delete book
			given().header("Content-Type", "application/json")
					.body("{\n"
							+ "    \"ID\": \""+ID+"\"\n"
							+ "}")
					.when().delete("Library/DeleteBook.php")
					.then().log().all();
			
		}

	}


