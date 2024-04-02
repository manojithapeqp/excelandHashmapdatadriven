package com.booklibrarydatadriven;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class PassPLDataViaNestedJSON {

	public static void main(String[] args) {
		// Code to create Place ID via POST

		// Below we mention base url
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		Map<String, Object> payload = new HashMap<>();

		// Constructing the location object for Add location Nested JSON
		Map<String, Double> location = new HashMap<>();
		location.put("lat", -38.383494);
		location.put("lng", 33.427362);

		// Adding fields to the payload
		payload.put("location", location);
		payload.put("accuracy", 50);
		payload.put("name", "Akshay Bunglow");
		payload.put("phone_number", "(+91) 983 893 3937");
		payload.put("address", "29, side layout, cohen 09");
		
	// Here we pass types arrayList as a value
		//payload.put("types", Arrays.asList("shoe park", "shop"));
		
	//Here we pass data as array input as a value for "Type"
		String arr[]= {"shoe park", "shop"};
		payload.put("types", arr);

		payload.put("website", "http://google.com");
		payload.put("language", "French-IN");

		String response = given().queryParams("key", "qaclick123").header("Content-Type", "application/json")
				.body(payload)
				// We submit that api
				.when().post("maps/api/place/add/json")
				// here we get response
				.then().log().all().statusCode(200).body("scope", equalTo("APP"))
				.header("server", "Apache/2.4.52 (Ubuntu)").header("Content-Type", "application/json;charset=UTF-8")
				.extract().response().asString();
		JsonPath js = new JsonPath(response);
		String placeID = js.getString("place_id");
		System.out.println("Newly created PlaceID is : " + placeID);
		System.out.println("Created Payload is like: " + payload);
	}
}
