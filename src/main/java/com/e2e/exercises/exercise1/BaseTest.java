package com.e2e.exercises.exercise1;

import net.minidev.json.JSONObject;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.e2e.exercises.exercise1.baseclass.Commonclass;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseTest {

	@Test
	public void test1() throws Exception {
		JSONObject jobject = Commonclass.jsonObject();
		
		Commonclass.writeFile(Commonclass.propReader("filePath"),Commonclass.tostring(jobject));
		
		String propReader = Commonclass.propReader("filePath");
		JSONObject readFile = Commonclass.readFile(propReader);
		System.out.println(readFile.toString());
		String actual =(String) readFile.get("device-code");
		Assert.assertEquals(actual, "HZ096YKML");
		
		/** 
		 * FIRST TEST COMES HERE:
		 * 
		 * Create the below JSON object using any library.
		 * Print the same in the console and also save the object in a file in src/main/resources.
		 * Assert the created JSON is as expected.
		 * */

	}

	@Test(dependsOnMethods = "test1")
	public void test2() throws Exception {
		
		File f = new File(Commonclass.propReader("filePath"));

		ObjectMapper mp = new ObjectMapper();
		mp.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		TestDeserialized Object = mp.readValue(f, TestDeserialized.class);
		Assert.assertEquals("HZ096YKML", Object.getDevicecode());
		Assert.assertEquals("JBL T160BT Bluetooth Headset", Object.getDevicename());
		Assert.assertEquals("JBL T160BT Bluetooth Headset - Black, In the Ear", Object.getDeviceinfo());
		Assert.assertEquals("red", Object.getColorlist().get(0).toString());
		Assert.assertEquals("black", Object.getColorlist().get(1).toString());
		Assert.assertEquals("white", Object.getColorlist().get(2).toString());
		Assert.assertEquals("Instock", Object.getStock());

		
		/**
		 * SECOND TEST COMES HERE:
		 * 
		 * Read the JSON object from the file and deserialize it into a Java object
		 * Assert the deserialized object
		 * */
		
	}
}
