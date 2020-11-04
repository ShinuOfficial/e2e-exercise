package com.e2e.exercises.exercise1;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

public class BaseTest {

	@SuppressWarnings("unchecked")
	@Test
	public void test1() throws IOException {

		/**
		 * FIRST TEST COMES HERE:
		 * 
		 * Create the below JSON object using any library. Print the same in the console
		 * and also save the object in a file in src/main/resources. Assert the created
		 * JSON is as expected.
		 * 
		 * 
		 */

		HashMap<String, Object> jsonObject = new LinkedHashMap<String, Object>();
		jsonObject.put("device-code", "HZ096YKML");
		jsonObject.put("device-name", "JBL T160BT Bluetooth Headset");
		jsonObject.put("device-info", "JBL T160BT Bluetooth Headset - Black, In the Ear");

		List<String> colorCodes = new ArrayList<String>();
		colorCodes.add("red");
		colorCodes.add("black");
		colorCodes.add("white");
		jsonObject.put("color-codes", colorCodes);

		jsonObject.put("stock", "Instock");
		jsonObject.put("serviceable", true);
		jsonObject.put("available-quantities", 100);

		// Print using JSON Simple

		JSONObject jsonSimpleObject = new JSONObject();
		String finalpayload = jsonSimpleObject.toJSONString(jsonObject);

		System.out.println(finalpayload);

		// Write file

		String filePath = System.getProperty("user.dir") + "//src//main//resources//payloadfile.json";

		FileWriter file = new FileWriter(filePath);

		file.write(finalpayload);

		file.flush();

		// Read from File using javanio Paths and Files class

		Path path = Paths.get(filePath);

		String fromFile = Files.readString(path);

		System.out.println(fromFile);

		Assert.assertEquals("Content is same", fromFile, finalpayload);

	}

	@Test(dependsOnMethods = { "test1" })
	public void test2() {

		/**
		 * SECOND TEST COMES HERE:
		 * 
		 * Read the JSON object from the file and deserialize it into a Java object
		 * Assert the deserialized object
		 * 
		 * 
		 **/

		String filePath = System.getProperty("user.dir") + "//src//main//resources//payloadfile.json";

		File file1 = new File(filePath);

		ObjectMapper mp = new ObjectMapper();
		mp.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		try {
			DevicePOJO Object = mp.readValue(file1, DevicePOJO.class);

			System.out.println(Object.getDevicecode());
			System.out.println(Object.getDevicename());
			System.out.println(Object.getDeviceinfo());

			System.out.println(Object.getColorlist().get(0).toString());
			System.out.println(Object.getColorlist().get(1).toString());
			System.out.println(Object.getColorlist().get(2).toString());
			System.out.println(Object.getStock());
			System.out.println(Object.isServiceable());
			System.out.println(Object.getAvailablequantitties());
			

		

			Assert.assertEquals("Device Code match", "HZ096YKML", Object.getDevicecode());
			Assert.assertEquals("Device Name match", "JBL T160BT Bluetooth Headset", Object.getDevicename());
			Assert.assertEquals("Device info match", "JBL T160BT Bluetooth Headset - Black, In the Ear",
					Object.getDeviceinfo());

			Assert.assertEquals("Device Colorcode1 match", "red", Object.getColorlist().get(0).toString());
			Assert.assertEquals("Device Colorcode2 match", "black", Object.getColorlist().get(1).toString());
			Assert.assertEquals("Device Colorcode3 match", "white", Object.getColorlist().get(2).toString());

			Assert.assertEquals("Device stock match", "Instock", Object.getStock());

			Assert.assertEquals("Device Serviceable match", true, Object.isServiceable());
			Assert.assertEquals("Device quanitymatch", 100, Object.getAvailablequantitties());

		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
