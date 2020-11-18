package com.e2e.exercises.exercise1;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class RequestHelper {

	private URI uri;

	private Response resp;
	
	

	public Response getCallResponseAsString(String url) {

		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		resp = given().get(uri).thenReturn();

		return resp;

	}

	public String printPretty(Response resp) {

		return resp.body().prettyPrint();

	}

	public int getResponseSize(Response resp) {

		int listSize = Arrays.asList(resp).size();
		return listSize;

	}

	public Response postCallResponse(String url) {

		HashMap<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();
		// ObjectNode obj=mapper.createObjectNode();

		ObjectNode obj1 = mapper.createObjectNode();
		obj1.put("userId", 10);
		obj1.put("id", 100);
		obj1.put("title", "Test Title");
		obj1.put("body", "This is test body");

		String payLoad = obj1.toPrettyString();

		Response resp = given().headers(header).when().body(payLoad).log().all().post(Constants.ENDPOINT);

		return resp;

	}

	public Response putCallResponse(String url) {

		HashMap<String, String> header = new HashMap<String, String>();
		header.put("Content-Type", "application/json");

		ObjectMapper mapper = new ObjectMapper();
		// ObjectNode obj=mapper.createObjectNode();

		ObjectNode obj1 = mapper.createObjectNode();
		obj1.put("userId", 10);
		obj1.put("id", 100);
		obj1.put("title", "Test Title - updated");
		obj1.put("body", "This is test body - updated");

		String payLoad = obj1.toPrettyString();

		Response resp = given().headers(header).when().body(payLoad).log().all().post(Constants.ENDPOINT);

		return resp;

	}

	public Response deleteCallresponse(String url) throws URISyntaxException {

		uri = new URI(url);

		resp = given().when().delete(uri);
		return resp;

	}

	public Response getSinglePostresponse(String url) throws URISyntaxException {

		uri = new URI(url);

		resp = given().when().get(uri);
		return resp;

	}

	public List parseJsonViaGson(Response resp) {
		List<Object> list = new LinkedList<Object>();
		Gson gson = new Gson();
		JsonElement e = gson.fromJson(resp.asString(), JsonElement.class);
		JsonObject jsonObject = e.getAsJsonObject();

		int jsonelement_userId = jsonObject.get("userId").getAsInt();
		int jsonelement_id = jsonObject.get("id").getAsInt();
		String jsonelement_title = jsonObject.get("title").getAsString();
		String jsonelement_body = jsonObject.get("body").getAsString();

		list.add(jsonelement_userId);
		list.add(jsonelement_id);
		list.add(jsonelement_title);
		list.add(jsonelement_body);
		return list;

	}
}

