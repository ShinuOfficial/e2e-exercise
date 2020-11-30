package com.e2e.exercises.exercise1;

import java.io.FileWriter;
import java.io.IOException;

import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class BaseTest {

	public static Gson gson;


	@Test
	public void test1() throws IOException {
		
		//Constructing a JSON Object
		
		JsonObject 	obj = new JsonObject();
		obj.addProperty("device-code", "HZ096YKM");
		obj.addProperty("device-name", "JBL T160BT Bluetooth Headset");
		obj.addProperty("device-info", "JBL T160BT Bluetooth Headset - Black, In the Ear");
		obj.addProperty("stock", "Instock");
		obj.addProperty("serviceable", true);
		obj.addProperty("available-quantities", 100);
		
		//Constructing a JSON array
		
		JsonArray colorCodes = new JsonArray();
		colorCodes.add("red");
		colorCodes.add("black");
		colorCodes.add("white");
	
		obj.add("colorCodes", colorCodes);
		
		//Serializing & prettyPrinting it in a console
		GsonBuilder gb = new GsonBuilder();
		gb.setPrettyPrinting();
		
		gson = gb.create();
		String deviceDetails = gson.toJson(obj);
		
		//gb.create().toJson(obj);
		System.out.println(deviceDetails);

		//Saving the object in src/main/resources
		FileWriter jsonFile = new FileWriter(System.getProperty("user.dir") + "/src//main/resources/outputFile.json");
		jsonFile.write(deviceDetails);
		jsonFile.close();
	
		
	}

	@Test
	public void test2() {
		
		/**
		 * SECOND TEST COMES HERE:
		 * 
		 * Read the JSON object from the file and deserialize it into a Java object
		 * Assert the deserialized object
		 * */
		
	}
}
