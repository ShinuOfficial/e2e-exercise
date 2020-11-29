package com.e2e.exercises.exercise1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Iterator;

import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;
import org.testng.annotations.Test;


import org.json.*;
import org.json.simple.parser.*;

public class BaseTest2 {

	@Test
	public void test1() throws Exception {
		JSONObject obj = new JSONObject();

		obj.put("device-code", "HZ096YKML");
		obj.put("device-name", "JBL T160BT Bluetooth Headset");
		obj.put("device-info", "JBL T160BT Bluetooth Headset - Black, In the Ear");
		obj.put("stock", "Instock");
		obj.put("serviceable", new Boolean(true));
		obj.put("available-quantities", new Integer(100));

		JSONArray colorCodes = new JSONArray();
		colorCodes.put("red");
		colorCodes.put("black");
		colorCodes.put("white");
		obj.put("color-codes", colorCodes);

		try {
			FileWriter file = new FileWriter("src/main/resources/output.json");
			file.write(obj.toString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}


		String actual = obj.toString();
		System.out.print(actual);
		
	      System.out.println("Pretty Print of JSON:");
			System.out.print(obj.toString(4));
	    

		try {
			JSONAssert.assertEquals(
					"{device-name:\"JBL T160BT Bluetooth Headset\",device-code:\"HZ096YKML\",device-info:\"JBL T160BT Bluetooth Headset - Black, In the Ear\",serviceable:true,stock:\"Instock\",available-quantities:100, color-codes:[\"red\",\"black\",\"white\"]}",
					actual, false);
		} catch (JSONException e) {
			e.getCause();
		}
	}

	@Test
	public void test2() throws Exception {
		org.json.simple.JSONObject jo1 =  (org.json.simple.JSONObject) new JSONParser().parse(new FileReader("src/main/resources/output.json"));
		//JSONObject jo = (JSONObject) txt;
		JSONObject jo = new JSONObject(jo1.toJSONString());
		String deviceCode = (String) jo.get("device-code");
		String deviceName = (String) jo.get("device-name");
		String deviceInfo = (String) jo.get("device-info");
		String Stock = (String) jo.get("stock");
		int availableQuantities =  (int) jo.get("available-quantities");
		boolean serviceable = (boolean) jo.get("serviceable");
		JSONArray colorCodes = (JSONArray) jo.get("color-codes");

		System.out.println("device-code = " + deviceCode);
		System.out.println("device-name = " + deviceName);
		System.out.println("device-info = " + deviceInfo);
		System.out.println("available-quantities = " + availableQuantities);
		System.out.println("Stock = " + Stock);
		System.out.println("serviceable = " + serviceable);
		System.out.println("color-codes = " + colorCodes);

		Assert.assertEquals("HZ096YKML", deviceCode);
		Assert.assertEquals("JBL T160BT Bluetooth Headset", deviceName);
		Assert.assertEquals("JBL T160BT Bluetooth Headset - Black, In the Ear", deviceInfo);
		Assert.assertEquals("Instock", Stock);
		Assert.assertEquals(true, serviceable);
		Assert.assertEquals(100, availableQuantities);

		Object[] arr = new String[colorCodes.length()];
		for (int i = 0; i < colorCodes.length(); i++) {
			arr[i] = colorCodes.get(i);
		}

		Object[] expected = new Object[] { "red", "black", "white" };
		Assert.assertEquals(expected, arr);

	}

}