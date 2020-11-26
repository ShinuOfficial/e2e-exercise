package com.e2e.exercises.exercise2;

import java.util.ArrayList;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
//import com.e2e.exercises.exercise2.ApiWrap;

public class ApiTest {

	ApiWrap a;

	@BeforeMethod
	public void setup() {
		a = new ApiWrap();
	}

	@Test
	public void jsonPlaceHolderGET() throws ParseException {
		// Get All Resources
		a.setSpec();
		Response s = a.getResource();

		Assert.assertEquals(s.statusCode(), 200);
		Assert.assertEquals(s.contentType(), "application/json; charset=utf-8");

		@SuppressWarnings("deprecation")
		JSONParser j = new JSONParser();
		JSONArray body = (JSONArray) j.parse(s.asString());
		JSONObject obj = (JSONObject) body.get(0);
		Assert.assertEquals(obj.get("id"), 1);
		Assert.assertEquals(obj.size(), 4);

		JsonPath jsonPathEvaluator = s.jsonPath();
		ArrayList<Integer> a1 = new ArrayList<Integer>();
		a1 = jsonPathEvaluator.get("id");
		if (a1.get(0) == 1) {
			System.out.println("True");
		}
		// Parse the Response ansd fetch a ID
		String s1 = new String();
		s1 = s.asString();
		int id = a.parsing(s1);
		// Set Spec with ID
		a.setSpec(id);
		// Get a Single resource
		System.out.println(a.GetSingleResource());
	}

	@Test
	public void jsonPlaceHolderPost() {

		a.setPost();
		Response s = a.postResource();
		Assert.assertEquals(s.statusCode(), 201);
		Assert.assertEquals(s.contentType(), "application/json; charset=utf-8");
	}

	@Test
	public void jsonPlaceholderUpdate() {

		a.setUpdate();
		Response s = a.updateResource();
		Assert.assertEquals(s.statusCode(), 200);
		Assert.assertEquals(s.contentType(), "application/json; charset=utf-8");
	}

	@Test
	public void jsonPlaceholderDelete() {

		a.setSpec();
		Response s = a.deleteResource();
		Assert.assertEquals(s.statusCode(), 200);
		Assert.assertEquals(s.contentType(), "application/json; charset=utf-8");
		Assert.assertTrue(s!=null);;
	}

}
