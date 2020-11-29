package com.e2e.exercises.exercise1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import net.minidev.json.JSONObject;


public class Task2_Run  {
	
	
	@Test
	static void test_get_1() {
	
		Response response = Task2_WrapperClass.makeGetRequest("https://jsonplaceholder.typicode.com/posts","application/json");
		response.getBody().prettyPrint();
		int responseCode = response.getStatusCode();
		Assert.assertEquals(responseCode, 200);
		Assert.assertNotNull(response.getBody());
		
		List<Integer> userId = response.jsonPath().getList("userId");
		Assert.assertEquals(userId.get(0), new Integer(1));
		List<Integer> Id = response.jsonPath().getList("id");
		Assert.assertEquals(Id.get(0), new Integer(1));
		List<String> title = response.jsonPath().getList("title");
		Assert.assertEquals(title.get(0), "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
		List<String> body = response.jsonPath().getList("body");
		Assert.assertEquals(body.get(0), "quia et suscipit\n"
				+ "suscipit recusandae consequuntur expedita et cum\n"
				+ "reprehenderit molestiae ut ut quas totam\n"
				+ "nostrum rerum est autem sunt rem eveniet architecto");
		System.out.println(userId.get(0));
		System.out.println(Id.get(0));
		System.out.println(title.get(0));
		System.out.println(body.get(0));
		
	
}
	
	@Test
	static void test_get_2() {
	
		Response response = Task2_WrapperClass.makeGetRequest("https://jsonplaceholder.typicode.com/posts/1","application/json");
		response.getBody().prettyPrint();
		String responseString = response.getBody().asString();
		Integer userId = response.jsonPath().getInt("userId");
		Assert.assertEquals(userId, new Integer(1));
		Integer Id = response.jsonPath().getInt("id");
		Assert.assertEquals(Id, new Integer(1));
		String title = response.jsonPath().getString("title");
		Assert.assertEquals(title, "sunt aut facere repellat provident occaecati excepturi optio reprehenderit");
		String body = response.jsonPath().getString("body");
		Assert.assertEquals(body, "quia et suscipit\n"
				+ "suscipit recusandae consequuntur expedita et cum\n"
				+ "reprehenderit molestiae ut ut quas totam\n"
				+ "nostrum rerum est autem sunt rem eveniet architecto");
		System.out.println(userId);
		System.out.println(Id);
		System.out.println(title);
		System.out.println(body);
		String actual = "{\n"
				+ "    \"userId\": 1,\n"
				+ "    \"id\": 1,\n"
				+ "    \"title\": \"sunt aut facere repellat provident occaecati excepturi optio reprehenderit\",\n"
				+ "    \"body\": \"quia et suscipit\\nsuscipit recusandae consequuntur expedita et cum\\nreprehenderit molestiae ut ut quas totam\\nnostrum rerum est autem sunt rem eveniet architecto\"\n"
				+ "}";
		Assert.assertEquals(responseString, actual);

}

	@Test
	static void test_post() {
		Map<String,Object> headerMap = new HashMap<String,Object>();
		headerMap.put("content-type", "application/json");
		
		JSONObject requestParamsPut = new JSONObject();
		requestParamsPut.put("userId", new Integer(10));
		requestParamsPut.put("id", new Integer(100));
		requestParamsPut.put("title", "Test Title");
		requestParamsPut.put("body", "This is test body");
		//System.out.println(requestParamsPut);
		System.out.println(requestParamsPut.toString());
		String requestParams = requestParamsPut.toJSONString();
		
		Response response = Task2_WrapperClass.makePostRequest("https://jsonplaceholder.typicode.com/posts",headerMap,requestParams);
		response.getBody().prettyPrint();
		String responseString = response.getBody().asString();
		String actual = "{\n"
				+ "    \"id\": 101,\n"
				+ "    \"title\": \"Test Title\",\n"
				+ "    \"body\": \"This is test body\",\n"
				+ "    \"userId\": 10\n"
				+ "}";
		Assert.assertEquals(responseString, actual);
		Assert.assertEquals(response.getStatusCode(), 201);
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
	
	
}
	
	
	@Test
	static void test_put() {
		Map<String,Object> headerMap = new HashMap<String,Object>();
		headerMap.put("content-type", "application/json");
		
		JSONObject requestParamsPut = new JSONObject();
		requestParamsPut.put("userId", new Integer(10));
		requestParamsPut.put("id", new Integer(100));
		requestParamsPut.put("title", "Test Title - updated");
		requestParamsPut.put("body", "This is test body - updated");
		System.out.println(requestParamsPut.toString());
		String requestParams = requestParamsPut.toJSONString();
		
		Response response = Task2_WrapperClass.makePutRequest("https://jsonplaceholder.typicode.com/posts/10",headerMap,requestParams);
		response.getBody().prettyPrint();
		String responseString = response.getBody().asString();
		String actual = "{\n"
				+ "    \"id\": 10,\n"
				+ "    \"title\": \"Test Title - updated\",\n"
				+ "    \"body\": \"This is test body - updated\",\n"
				+ "    \"userId\": 10\n"
				+ "}";
		Assert.assertEquals(responseString, actual);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
	
	
}
	
	
	@Test
	static void test_delete() {
	
		Response response = Task2_WrapperClass.makeDeleteRequest("https://jsonplaceholder.typicode.com/posts/10","application/json");
		response.getBody().prettyPrint();
		String responseString = response.getBody().asString();
		String actual = "{\n"
				+ "    \n"
				+ "}";
		Assert.assertEquals(responseString, actual);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getContentType(), "application/json; charset=utf-8");
	
}
	
//	
//	@Test
//	static void test_post_differentTry() {
//		Map<String,Object> headerMap = new HashMap<String,Object>();
//		headerMap.put("content-type", "application/json");
//		
//		JSONObject requestParamsPut = new JSONObject();
//		requestParamsPut.put("userId", new Integer(10));
//		requestParamsPut.put("id", new Integer(100));
//		requestParamsPut.put("title", "Test Title");
//		requestParamsPut.put("body", "This is test body");
//		//System.out.println(requestParamsPut);
//		System.out.println(requestParamsPut.toString());
//		String requestParams = requestParamsPut.toJSONString();
//		
//		RequestSpecification requestSpec = Task2_Copy.constructSpec("https://jsonplaceholder.typicode.com/posts",headerMap,requestParams);
//		Response response = requestSpec.post();
//		response.getBody().prettyPrint();
//		response.then().log().all();		
//		
//	
//}
//
}