package com.e2e.exercises.exercise1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.e2e.exercises.exercise1.baseclass.Commonclass;

public class BaseTest {

	@Test
	public void test1() throws Exception {
		JSONObject jobject = new JSONObject();
		jobject.put("device-code", "HZ096YKML");
		jobject.put("device-name", "JBL T160BT Bluetooth Headset");
		jobject.put("device-info", "JBL T160BT Bluetooth Headset - Black, In the Ear");
		JSONArray jArrColorCodes = new JSONArray();
		jArrColorCodes.put("red");
		jArrColorCodes.put("black");
		jArrColorCodes.put("white");
		jobject.put("color-codes", jArrColorCodes);
		jobject.put("stock", "Instock");
		jobject.put("serviceable", true);
		jobject.put("available-quantities", 100);
		
		Commonclass.writeFile(Commonclass.propReader("filePath"),Commonclass.tostring(jobject));
		
		JSONObject jo = new JSONObject(Commonclass.readFile(Commonclass.propReader("filePath")));
		String device_code =  jo.get("device-code").toString();
		Assert.assertEquals(device_code, "HZ096YKML");
		
		/** 
		 * FIRST TEST COMES HERE:
		 * 
		 * Create the below JSON object using any library.
		 * Print the same in the console and also save the object in a file in src/main/resources.
		 * Assert the created JSON is as expected.
		 * */

	}

	@Test(dependsOnMethods ="test1" )
	public void test2() {
		
		/**
		 * SECOND TEST COMES HERE:
		 * 
		 * Read the JSON object from the file and deserialize it into a Java object
		 * Assert the deserialized object
		 * */
		
	}
}
