package com.e2e.exercises.exercise1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Iterator;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

public class BaseTest {

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
		colorCodes.add("red");
		colorCodes.add("black");
		colorCodes.add("white");
		obj.put("color-codes", colorCodes);
		
		File theDir = new File("src/main/resources");
		if (!theDir.exists()){
		    theDir.mkdirs();
		}

		try {
			FileWriter file = new FileWriter("src/main/resources/output.json");
			file.write(obj.toJSONString());
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringWriter out = new StringWriter();
		obj.writeJSONString(out);

		String actual = out.toString();
		System.out.print(actual);
		
	      System.out.println("Pretty Print of JSON:");
	      //System.out.println(obj.to);

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
		Object txt = new JSONParser().parse(new FileReader("src/main/resources/output.json"));
		JSONObject jo = (JSONObject) txt;
		String deviceCode = (String) jo.get("device-code");
		String deviceName = (String) jo.get("device-name");
		String deviceInfo = (String) jo.get("device-info");
		String Stock = (String) jo.get("stock");
		int availableQuantities = (int) jo.get("available-quantities");
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

		Object[] arr = new String[colorCodes.size()];
		for (int i = 0; i < colorCodes.size(); i++) {
			arr[i] = colorCodes.get(i);
		}

		Object[] expected = new Object[] { "red", "black", "white" };
		Assert.assertEquals(expected, arr);

	}

}