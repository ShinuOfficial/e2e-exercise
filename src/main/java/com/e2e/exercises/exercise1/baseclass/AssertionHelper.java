package com.e2e.exercises.exercise1.baseclass;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;

import com.jayway.restassured.response.Response;

public class AssertionHelper {
	
	public static void assertId(Response response) {
		JSONArray jarray =new JSONArray(response.getBody().asString());
		for (int i = 0; i < jarray.length(); i++) {
			 int id = jarray.getJSONObject(i).getInt("id");
			Assert.assertEquals(i+1, id);
			
		}
	}
	
	public static void assertSingleId(Response response, String singleid ) {
		JSONObject jobject =new JSONObject(response.getBody().asString());
		int responseId = jobject.getInt("id");
		Assert.assertEquals(responseId, Integer.parseInt(singleid));
	}
	
	public static void assertSingleIdTitle(Response response, String singleid ) {
		JSONObject jobject =new JSONObject(response.getBody().asString());
		String responseId = jobject.getString("title");
		Assert.assertEquals(responseId, singleid);
	}

}
