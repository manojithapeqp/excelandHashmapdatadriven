package com.booklibrarydatadriven;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class LibraryAPIPassPayloadDirectBody {

	public static void main (String[] args) {
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().header("Content-Type", "application/json")
				.body("{\n"
						+ "\"name\":\"Learn Appium Automation with Java Test\",\n"
						+ "\"isbn\":\"bcdsfd\",\n"
						+ "\"aisle\":\"292674\",\n"
						+ "\"author\":\"John foer\"\n"
						+ "}")
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
