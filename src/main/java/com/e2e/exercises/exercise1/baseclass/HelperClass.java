package com.e2e.exercises.exercise1.baseclass;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.testng.Assert;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class HelperClass extends Commonclass{
	
	public static Integer fetchSingleID(Response response) {
		JSONArray jarray =new JSONArray(response.getBody().asString());
			return jarray.getJSONObject(5).getInt("id");
	}
	
	public static Map<String, String> getHeaders(){
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		return headers;
	}
	
	public static Response getRequest(String url) {
		Response response = RestAssured
				.given().relaxedHTTPSValidation().contentType(ContentType.JSON).request().when().get(url);
		Assert.assertEquals(response.getStatusCode(), 200);
		return response;
	}
	
	public static Response postRequest(String url) {
		 RequestSpecification request = RestAssured.given();
		 request.body(Commonclass.jsonObjectParam().toString());
		 Response response = request.post(url);
		Assert.assertEquals(response.getStatusCode(), 201);
		return response;
	}
	
	public static Response putRequest(String url) {
		 RequestSpecification request = RestAssured.given();
		 request.headers(getHeaders());
		 request.body(Commonclass.jsonObjectPutParam().toString());
		 Response response = request.put(url);
		 System.out.println(response.getBody().asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		return response;
	}
}
