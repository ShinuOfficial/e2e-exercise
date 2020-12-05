package com.e2e.exercises.exercise2;

import java.util.Random;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

public class ApiWrap {

	static RequestSpecification spec;

	
	public void setSpec() {
		spec = RestAssured.given().baseUri("https://jsonplaceholder.typicode.com").basePath("posts");
	}

	public void setSpec(int id) {
		spec = RestAssured.given().baseUri("https://jsonplaceholder.typicode.com").basePath("posts/" + id);
	}

	public void setPost() {
		JSONObject payload = new JSONObject();
		payload.put("userId", 10);
		payload.put("id", 101);
		payload.put("title", "Test Title");
		payload.put("body", "This is Test Body");

		spec = RestAssured.given().contentType("application/json; charset=utf-8")
				.baseUri("https://jsonplaceholder.typicode.com/posts").body(payload.toJSONString());
	}

	public void setUpdate() {
		JSONObject payload = new JSONObject();
		payload.put("userId", 10);
		payload.put("id", 101);
		payload.put("title", "Test is updated Title");
		payload.put("body", "This is updated Test Body");

		spec = RestAssured.given().contentType("application/json; charset=utf-8")
				.baseUri("https://jsonplaceholder.typicode.com/posts").body(payload.toJSONString());

	}
	
	/*public int parsing2(String s1) throws ParseException {
		JSONParser j = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
		Object res =  j.parse(s1);
		JSONArray j1 = (JSONArray)res;

		int i = new Random().nextInt(20);
		JSONObject obj = (JSONObject) j1.get(i);
		return (int) obj.get("id");
	}*/

	public int parsing(String s1) throws ParseException {
		JSONParser j = new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE);
		JSONArray res = (JSONArray) j.parse(s1);

		int i = new Random().nextInt(20);
		JSONObject obj = (JSONObject) res.get(i);
		return (int) obj.get("id");
	}


	
	public Response getResource() {
		return (Response) RestAssured.given().spec(spec).when().get().then().statusCode(200).extract();
	}

	public String GetSingleResource() {
		return  RestAssured.given().spec(spec).when().get().then().statusCode(200).extract().asString();
	}

	public Response postResource() {
		return (Response) RestAssured.given().spec(spec).when().post().then().statusCode(201).extract();
	}

	public Response updateResource() {
		return (Response) RestAssured.given().spec(spec).when().put("/100").then().statusCode(200).extract();
	}

	public Response deleteResource() {
		return (Response) RestAssured.given().spec(spec).when().delete("/10").then().statusCode(200).extract();
	}
}
