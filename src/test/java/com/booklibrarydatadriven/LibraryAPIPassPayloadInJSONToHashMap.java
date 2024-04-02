package com.booklibrarydatadriven;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LibraryAPIPassPayloadInJSONToHashMap {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
	//Convert JSON payload into HashMap
		HashMap<String,Object> jsonAsMap =new HashMap<>();
		jsonAsMap.put("name", "Time Money");
		jsonAsMap.put("isbn", "dskkl");
		jsonAsMap.put("aisle", "2926");
		jsonAsMap.put("author", "Shlok Ithape");
		String response = given().header("Content-Type", "application/json")
				.body(jsonAsMap)
				.when().post("Library/Addbook.php")
				.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js = new JsonPath(response);
		String expMsg = "successfully added";
		String ID = js.get("ID");
		System.out.println(ID);
		String actualMessage = js.get("Msg");
		System.out.println(actualMessage);

	}

}
