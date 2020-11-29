package com.e2e.exercises.exercise1;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
//import org.json.JSONObject;

import java.util.Map;

import io.restassured.specification.RequestSpecification;


public class Task2_WrapperClass {
	
    static RequestSpecification getCommonSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri ("https://jsonplaceholder.typicode.com/posts");


        RequestSpecification requestSpec = builder.build();

        return requestSpec;

    }
    
    static Response makeGetRequest(String url, String contentType) {

    	return given()
    	.baseUri(url)
        .get();
    	
    	
    }
    
    static Response makePostRequest(String url, Map contentType, String body) {

    	return given()
    	.baseUri(url)
    	.headers(contentType)
    	.body(body)
        .post();
    	
    	
    }
    
    static Response makePutRequest(String url, Map contentType, String body) {

    	return given()
    	.baseUri(url)
    	.headers(contentType)
    	.body(body)
        .put();
    	
    	
    }
    
    static Response makeDeleteRequest(String url, String contentType) {

    	return given()
    	.baseUri(url)
        .delete();
    		
    }
    
    
    static RequestSpecification constructSpec(String url, Map contentType, String body) {

    	RequestSpecification requestSpec =  given()
    	.baseUri(url)
    	.headers(contentType)
    	.body(body);
    	
//    	Response response = null ;
    	
//    	if(method=="get")
//    	{
//    		response = requestSpec.get();
//    	}
    	
    	return requestSpec;
    }
    
    
    int addTwoNumbers(int a, int b) {
    	int answer = a+b ;
    	return answer;
    }
	
}