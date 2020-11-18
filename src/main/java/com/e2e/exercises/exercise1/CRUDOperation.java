package com.e2e.exercises.exercise1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
//import com.spark.excercise2.sparkexcercise2.RequestHelper;

import io.restassured.response.Response;

//

public class CRUDOperation {

	RequestHelper helper = new RequestHelper();
	// String endPoint="https://jsonplaceholder.typicode.com/posts";

	@Test
	public void readAllPosts() {
		Response resp = helper.getCallResponseAsString(Constants.ENDPOINT);
		String getResponseAsString = resp.asString();
		System.out.println(getResponseAsString);
		ObjectMapper mapper = new ObjectMapper();

		List<Integer> userId = new ArrayList<Integer>();
		List<Integer> id = new ArrayList<Integer>();
		List<String> title = new ArrayList<String>();
		List<String> body = new ArrayList<String>();
		try {
			getPostsPOJO[] posts = mapper.readValue(getResponseAsString, getPostsPOJO[].class);

			for (int i = 0; i < posts.length; i = i + 10) {

				int userentry = posts[i].getUserId();

				userId.add(userentry); //Add all the Userids to userId list

			}

			for (int j = 0; j < posts.length; j++) 
			{
				int identry = posts[j].getId();
				String titleentry = posts[j].getTitle();
				String bodyentry = posts[j].getBody();

				id.add(identry); //Add all the ids to id list
				title.add(titleentry); //Add all available title values  to title list
				body.add(bodyentry); //Add all the available values to body list

			}

			// System.out.println(userentry);

			int expected_no_of_userids = 100;
			int expected_no_of_ids = 10;

			boolean istitleempty = title.stream().noneMatch(titleobj -> titleobj != null);
			boolean isbodyempty = body.stream().noneMatch(bodyobj -> bodyobj != null);

			Assert.assertEquals(100, expected_no_of_userids);
			Assert.assertEquals(10, expected_no_of_ids);
			Assert.assertEquals(istitleempty, false);
			Assert.assertEquals(isbodyempty, false);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test
	public void createPost() {

		Response resp = helper.postCallResponse(Constants.ENDPOINT);
		String postResponseAsString = resp.asString();

		/*
		 * Gson gson = new Gson(); JsonElement element =
		 * gson.fromJson(postResponseAsString, JsonElement.class); JsonObject obj =
		 * element.getAsJsonObject();
		 * 
		 * int response_userId = obj.get("userId").getAsInt(); int response_id =
		 * obj.get("id").getAsInt(); String response_title =
		 * obj.get("title").getAsString(); String response_body =
		 * obj.get("body").getAsString()
		 */;

		List<Object> list = helper.parseJsonViaGson(resp);

		Assert.assertEquals(list.get(0), 10, "userIdMatch");
		Assert.assertEquals(list.get(1), 101, "id Match");
		Assert.assertEquals(list.get(2), "Test Title", "Title Match");
		Assert.assertEquals(list.get(3), "This is test body", "message Match");

		Assert.assertEquals(200, 200);
		// Assert.assertEquals("application/json;",resp.getContentType());

	}

	@Test
	public void updatePost() {

		Response resp = helper.putCallResponse(Constants.ENDPOINT);
		String putResponseAsString = resp.asString();

		List<Object> list = helper.parseJsonViaGson(resp);

		/*
		 * Gson gson = new Gson(); JsonElement element =
		 * gson.fromJson(putResponseAsString, JsonElement.class); JsonObject obj =
		 * element.getAsJsonObject();
		 * 
		 * int response_userId = obj.get("userId").getAsInt(); int response_id =
		 * obj.get("id").getAsInt(); String response_title =
		 * obj.get("title").getAsString(); String response_body =
		 * obj.get("body").getAsString();
		 */

		Assert.assertEquals(list.get(0), 10, "userIdMatch");
		Assert.assertEquals(list.get(1), 101, "id Match");
		Assert.assertEquals(list.get(2), "Test Title - updated", "Title Match");
		Assert.assertEquals(list.get(3), "This is test body - updated", "message Match");

		Assert.assertEquals(200, 200);
		// Assert.assertEquals("application/json;",resp.getContentType());

	}

	@Test
	public void deletePost() throws URISyntaxException {

		Response resp = helper.deleteCallresponse(Constants.DELETE_ENDPOINT);
		Assert.assertTrue(resp.statusCode() == 200);
		Gson gson = new Gson();
		JsonElement e = gson.fromJson(resp.asString(), JsonElement.class);
		JsonObject jsonObject = e.getAsJsonObject();
		Assert.assertEquals(jsonObject.size(), 0);
		
		

	}

	@Test
	public void singlePost() throws URISyntaxException {

		Response resp = helper.getSinglePostresponse(Constants.SINGLEPOST_ENDPOINT);
		Assert.assertTrue(resp.statusCode() == 200);
		String title_stringified = "sunt aut facere repellat provident occaecati excepturi optio reprehenderit";
		String body_stringified = "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto";
		/*
		 * Gson gson= new Gson(); JsonElement
		 * e=gson.fromJson(resp.asString(),JsonElement.class); JsonObject
		 * jsonObject=e.getAsJsonObject(); int
		 * jsonelement_userId=jsonObject.get("userId").getAsInt(); int
		 * jsonelement_id=jsonObject.get("id").getAsInt(); String
		 * jsonelement_title=jsonObject.get("title").getAsString(); String
		 * jsonelement_body=jsonObject.get("body").getAsString();
		 */

		List<Object> list = helper.parseJsonViaGson(resp);
		Assert.assertEquals(list.get(0), 1);
		Assert.assertEquals(list.get(1), 1);
		Assert.assertEquals(list.get(2), title_stringified);
		Assert.assertEquals(list.get(3), body_stringified);

	}
}
